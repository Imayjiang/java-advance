package com.qger.java.concurrent.intrinsic;

/**
 * </p>
 * Date:2016/3/26
 *
 * @author: jiangbo
 */
public class ThreadWaitNotifyTest {
//    public static void main(String args[]) {
//        List container = new ArrayList<Object>();
//        Thread consumer = new Thread(new ConsumerTask(container));
//        Thread producer = new Thread(new ProducerTask(container));
//        consumer.start();
//        producer.start();
//    }
//
//    private static long now() {
//        return System.currentTimeMillis();
//    }
//
//
//    /*������ */
//    static class ConsumerTask implements Runnable {
//        private final List dataBank;
//
//        public ConsumerTask(List v) {
//            this.dataBank = v;
//        }
//
//        public void run() {
//            long startTime = System.currentTimeMillis();
//            while (true) {
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                if ((now() - startTime) / 1000 > 10) {
//                    break;
//                }
//                System.out.println("consumer meeting the synchronized block");
//                synchronized (dataBank) {
//                    System.out.println("++++++++++++++++++consumer enter and holds lock before wait:"
//                            + Thread.holdsLock(dataBank) + "+++++++++++++++++++");
//                    try {
//                        if (dataBank.isEmpty()) {
//                            dataBank.wait();
//                        }
//                        System.out.println("++++++++++++++++++consumer being notified and re-obtains lock after wait:"
//                                + Thread.holdsLock(dataBank) + "++++++++++++++++++++");
//                        dataBank.notify();
//                        dataBank.clear();
//                        System.out.println("++++++++++++++++++consumer has been notified the producer+++++++++++++++++");
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }
//
//    /*  ������ */
//    static class ProducerTask implements Runnable {
//
//        private final List savingBox;
//
//        public ProducerTask(List v) {
//            this.savingBox = v;
//        }
//
//        public void run() {
//            while (true) {
//                long startTime = System.currentTimeMillis();
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                if ((now() - startTime) / 1000 > 10) {
//                    break;
//                }
//                System.out.println("producer meeting the synchronized block");
//                synchronized (savingBox) {
//                    System.out.println("====================producer enter and holds the before wait:" + Thread.holdsLock(savingBox) + "====================");
//                    try {
//                        if (!savingBox.isEmpty()) {
//                            savingBox.wait();
//                        }
//                        System.out.println("====================producer being notified and re-obtain the lock:" + Thread.holdsLock(savingBox) + "====================");
//                        //noinspection unchecked
//                        savingBox.add("apples");
//                        System.out.println("====================producer is producing product=======================");
//                        Thread.sleep(3*1000);
//                        savingBox.notify();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        new Thread(new Thread1()).start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Thread2()).start();
    }

    private static class Thread1 implements Runnable {

        @Override
        public void run() {
            // ���������Thread1�������Thread2�ڲ�run����Ҫ��ͬһ������Ϊ���������������ﲻ����this����Ϊ��Thread2�����this�����Thread1��this����ͬһ������������MultiThread.class����ֽ�����󣬵�ǰ������������������ʱ��ָ��Ķ���ͬһ������
            synchronized (Object.class) {

                System.out.println("enter thread1...");
                System.out.println("thread1 is waiting");
                try {
                    // �ͷ��������ַ�ʽ����һ�ַ�ʽ�ǳ�����Ȼ�뿪�������ķ�Χ��Ҳ�����뿪��synchronized�ؼ��ֹ�Ͻ�Ĵ��뷶Χ����һ�ַ�ʽ������synchronized�ؼ��ֹ�Ͻ�Ĵ����ڲ����ü����������wait���������ʹ��wait�����ͷ�����
                    Object.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("thread1 is going on...");
                System.out.println("thread1 is being over!");
            }
        }

    }

    private static class Thread2 implements Runnable {

        @Override
        public void run() {
            synchronized (Object.class) {

                System.out.println("enter thread2...");
                System.out.println("thread2 notify other thread can release wait status..");
                // ����notify���������ͷ�����
                // ��ʹthread2���������sleep������Ϣ��10���룬��thread1��Ȼ����ִ�У���Ϊthread2û���ͷ���������Thread1�޷��ò�������
                //notify�����ͷ�����ֻ�Ǹ��ߵ��ù�wait�������߳̿���ȥ���������ľ����ˣ����������ϵõ�������Ϊ�����ڱ���������˻�û�ͷš����notify��������Ĵ��뻹�кܶ࣬��Ҫ��Щ����ִ�����Ż��ͷ���
                Object.class.notify();
                System.out.println("thread2 is sleeping ten millisecond...");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread2 is going on...");
                System.out.println("thread2 is being over!");
            }
        }

    }
    /**
     * ���н����
     *  enter thread1...
     thread1 is waiting
     enter thread2...
     thread2 notify other thread can release wait status..
     thread2 is sleeping ten millisecond...
     thread2 is going on...
     thread2 is being over!
     thread1 is going on...
     thread1 is being over!
     */
}
