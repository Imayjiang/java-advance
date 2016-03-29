package com.qger.java.concurrent.aqs;

import java.util.concurrent.locks.ReentrantLock;

/**
 * </p>
 * Date:2016/3/25
 *
 * @author: jiangbo
 */
public class ReentrantLockUsage {

    public static volatile Boolean FLAG = false;

    public static void main(String[] args) throws InterruptedException {
        final ReentrantLock reentrantLock = new ReentrantLock();
        final Object LOCK = new Object();
        synchronized (LOCK) {
            System.out.println("locked by thread:"+Thread.currentThread().getName());
            Thread.sleep(3*1000);
            Thread.interrupted();
            Thread.sleep(5000);
            LOCK.wait();
            System.out.println("sleeping");
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (LOCK) {
                    LOCK.notify();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("getting the lock");
                }
//                try {
//                    Thread.sleep(2 * 1000);
//                } catch (InterruptedException e) {
//                    System.out.println(e);
//                }
//                FLAG = true;
//                try {
//                    tryLock(reentrantLock);
////                    Thread.interrupted();
//                } catch (InterruptedException e) {
//                    System.out.println(e);
//                }
//                System.out.println("ending");
            }
        }, "thread-worker-001").start();
//        Thread.sleep(1000);
//        tryLock(reentrantLock);
    }

    private static void tryLock(ReentrantLock reentrantLock) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        reentrantLock.lockInterruptibly();
        System.out.println("it's me," + Thread.currentThread().getName());
        if (FLAG != null && FLAG) {
            Thread.interrupted();
        }
    }

}
