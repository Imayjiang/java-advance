package com.qger.java.network.intrinsic.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * </p>
 * Date:2016/3/31
 *
 * @author: jiangbo
 */
public class FileChannelTest {

    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("","r/w");
        FileChannel fileChannel = file.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(256);
        int bytesRead = fileChannel.read(byteBuffer);
        while(bytesRead != -1) {
            System.out.println("bytes read:"+bytesRead);
            byteBuffer.flip();
        }

    }

}
