package com.qger.java.concurrent.datastructure;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * </p>
 * Date:2016/3/25
 *
 * @author: jiangbo
 */
public class BlockingQueueUsage {

    public static void main(String[] args) throws InterruptedException {
        final BlockingQueue<Object> blockingQueue
                = new ArrayBlockingQueue<Object>(4);

        for (int i = 0; i < 10; i++) {
//            new Thread(
//                    new Runnable() {
//                        @Override
//                        public void run() {
                            blockingQueue.put(new Object());
//                        }
//                    }
//
//            ).start();
        }
        System.out.println(blockingQueue.size());
    }


}
