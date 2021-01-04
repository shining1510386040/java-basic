package com.demo.threadandlock.newcodePritice.orderprint;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 多线程循环打印AB
 * 思路1：基于Object的wait/notify,以及设置一个线程运行状态标识flag；
 * 思路2：基于ReetrantLock和Condition接口，以及设置线程运行标识flag；
 * Condition可以看做是Obejct类的wait()、notify()、notifyAll()方法的替代品，与Lock配合使用。
 * @date 2020/12/31 16:48
 * @see
 */
public class MainTest {

    public static void main(String[] args) throws InterruptedException {

//        test1();
//        test2();
//        test3();

        // ok
        test4();

    }

    private static volatile int flag = 1;


    /**
     * 基于Lock接口和Condition接口实现；
     */
    private static void test4() {

        ReentrantLock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                lock.lock();
                if (flag == 1) {
                    System.out.print("A");
                    flag = 2;
                    // 通知其他线程
                    c1.signal();
                    try {
                        c2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        // 阻塞
                        c1.await();
                        c2.signal();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.unlock();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                lock.lock();
                if (flag == 2) {
                    System.out.print("B");
                    flag = 1;
                    // 通知其他线程
                    c2.signal();
                    try {
                        c1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        // 阻塞
                        c2.await();
                        c1.signal();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.unlock();
            }
        }).start();
    }

    private static void test3() {

        // 信号量
        Semaphore aSema = new Semaphore(1);
        Semaphore bSema = new Semaphore(1);

        // 打印A
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    aSema.acquire();
                    System.out.print("A");
                    bSema.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        // 打印B
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    bSema.acquire();
                    System.out.println("B");
                    aSema.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    private static void test2() {

        final Object lock = new Object();
        PrintA printA = new PrintA(flag1, lock);
        PrintB printB = new PrintB(flag1, lock);
        new Thread(printA).start();
        new Thread(printB).start();

    }

    // cas 线程间可见的变量
    private static volatile boolean flag1 = true;

    /**
     * 基于wait和notify实现；
     */
    private static void test1() {

        final Object lock = new Object();

        new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 5; i++) {
                    try {
                        if (flag1) {
                            System.out.print("A");
                            flag1 = false;
                            lock.notify();
                        } else {
                            // 阻塞
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 5; i++) {
                    if (!flag1) {
                        System.out.print("B");
                        flag1 = true;
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

    }
}
