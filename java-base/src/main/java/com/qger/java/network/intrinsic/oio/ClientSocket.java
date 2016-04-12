package com.qger.java.network.intrinsic.oio;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * </p>
 * Date:2016/4/3
 *
 * @author: jiangbo
 */
public class ClientSocket {

    Socket socket;

    ClientSocket(int port) throws IOException {
        socket = new Socket("127.0.0.1", port);
        Client task = new Client();
//        task.socket = socket;
//        new Thread(task).start();
    }

    private static class Client implements Runnable {
        Socket socket;

        @Override
        public void run() {
            try {
                DataInputStream in =
                        new DataInputStream(socket.getInputStream());
                System.out.println("Server says " + in.readUTF());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
