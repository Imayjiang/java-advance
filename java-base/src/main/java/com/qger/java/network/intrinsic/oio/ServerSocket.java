package com.qger.java.network.intrinsic.oio;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * </p>
 * Date:2016/4/3
 *
 * @author: jiangbo
 */
public class ServerSocket {

    private java.net.ServerSocket serverSocket;

    private static BlockingQueue<Socket> socketQueue
            = new ArrayBlockingQueue<Socket>(100);

    public ServerSocket() {
        try {
            this.serverSocket = new java.net.ServerSocket(8000);
            Listener serverListener = new Listener();
            serverListener.serverSocket = this.serverSocket;
            new Thread(serverListener).start();
        } catch (IOException e) {
            e.printStackTrace();
            if (serverSocket != null && !serverSocket.isClosed()) {
                try {
                    serverSocket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private static class Listener implements Runnable {

        java.net.ServerSocket serverSocket;

        @Override
        public void run() {
            if (serverSocket == null) {
                throw new IllegalStateException("no server socket instantiated!");
            }
            while (true) {
                Socket clientConnection = null;
                try {
                    System.out.println("listening for socket coming!");
                    clientConnection = serverSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                    try {
                        serverSocket.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                if (clientConnection != null) {
                    System.out.println("one socket established!" + clientConnection.getInetAddress());
                    try {
                        DataOutputStream out = new DataOutputStream(clientConnection.getOutputStream());
                        out.writeUTF("接收到你的请求" + clientConnection.getRemoteSocketAddress());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    socketQueue.add(clientConnection);
                }
            }
        }
    }
}
