package com.qger.java.network.intrinsic.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * </p>
 * Date:2016/3/31
 *
 * @author: jiangbo
 */
public class NioServer {

    private Selector selector;

    private ServerSocketChannel serverSocket;

    public NioServer() {
        try {
            serverSocket = ServerSocketChannel.open();
            serverSocket.configureBlocking(false);
            serverSocket.bind(new InetSocketAddress(8008));
        } catch (IOException e) {
            try {
                serverSocket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            throw new IllegalStateException(e);
        }
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }

    public void bind() throws ClosedChannelException {
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                int interestOps = selector.select();
                                if (interestOps == 0) {
                                    continue;
                                }
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                            Set<SelectionKey> selectionKeys = selector.selectedKeys();
                            Iterator<SelectionKey> iterator = selectionKeys.iterator();
                            while (iterator.hasNext()) {
                                SelectionKey selectionKey = iterator.next();
                                System.out.println("selectionKey ready:" + selectionKey.interestOps());
                                handle(selectionKey);
                                iterator.remove();
                            }

                        }
                    }

                }
        ).start();
    }

    private void handle(SelectionKey selectionKey) {
        if((selectionKey.interestOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
            System.out.println("local accept");
        }

        if((selectionKey.interestOps() & SelectionKey.OP_CONNECT) == SelectionKey.OP_CONNECT) {
            System.out.println("local connect");
        }
    }

    public static void main(String[] args) throws ClosedChannelException {
        NioServer nioServer = new NioServer();
        nioServer.bind();
    }
}
