package com.geekerstar.function.module.multi_thread.basic;

/**
 * @author geekerstar
 * @date 2023/2/19 19:40
 */
public class WaitNotify {

    public static void main(String[] args) {
        Object lock = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 20; i++) {
                    System.out.println(i);
                    if (i == 10) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T2线程唤醒T1线程");
                lock.notifyAll();
            }
        });

        t1.start();
        t2.start();
    }
}
