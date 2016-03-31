package com.qger.java.network.netty;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * </p>
 * Date:2016/3/30
 *
 * @author: jiangbo
 */
public class NettyGetStarted {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new NettyServer().start();
            }
        }).start();
        System.out.println("server started!");

    }

    private static class NettyServer {

        public void start() {
            ServerBootstrap serverBootstrap = new ServerBootstrap(
                    new NioServerSocketChannelFactory(
                            Executors.newCachedThreadPool(),
                            Executors.newCachedThreadPool()
                    )
            );
            serverBootstrap.setOption("reuseAddress", true);
            serverBootstrap.setOption("child.tcpNoDelay", true);
            serverBootstrap.setOption("child.soLinger", 2);

            serverBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
                @Override
                public ChannelPipeline getPipeline() throws Exception {
                    return Channels.pipeline(new EchoServerHandler());
                }
            });
            serverBootstrap.bind(new InetSocketAddress(8080));
        }
    }

    private static class EchoServerHandler extends SimpleChannelUpstreamHandler {

        @Override
        public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
            System.out.println("connected!");
        }

        @Override
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
            System.out.println("got message :" + e.getMessage());
        }
    }

    public static class NettyClient {

        public void start() {

        }

    }
}
