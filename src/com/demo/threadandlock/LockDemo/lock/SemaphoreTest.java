package com.demo.threadandlock.LockDemo.lock;

import java.util.concurrent.*;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 信号量测试
 * <p>
 * 可以维护当前访问自身的线程个数，比如控制文件的并发访问次数；
 * 单个的信号量可以实现互斥锁（悲观锁 synchronized）的功能；
 * @date 2021/1/4 11:18
 * @see
 */
public class SemaphoreTest {

    public static void main(String[] args) {

//        test1();

//        test2();

        test3();
    }

    private static void test3() {
        Semaphore semaphore = new Semaphore(2);
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 7; i++) {

            pool.submit(() -> {
                try {
                    // 尝试获得许可，获取到返回true，获取不到返回false。不会阻塞；
                    if (semaphore.tryAcquire()) {
                        System.out.println(Thread.currentThread().getName() + "working...");
                        TimeUnit.SECONDS.sleep(1);
                        // 释放许可
//                        semaphore.release();
                    } else {
                        System.out.println("没有获取到许可//。");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        pool.shutdown();
    }

    private static void test2() {
        // 4个许可证的信号量；
        Semaphore semaphore = new Semaphore(4);
        ExecutorService threadPool = new ThreadPoolExecutor(8, 8, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(5));

        for (int i = 0; i < 8; i++) {

            threadPool.submit(() -> {
                try {
                    // 获取一个许可，若无可用许可，将阻塞
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "working......");
                    TimeUnit.SECONDS.sleep(2);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放一个许可
//                    semaphore.release();
                }

            });

        }


        threadPool.shutdown();
    }

    private static void test1() {

        // 资源
        Object res = new Object();

        Semaphore lock = new Semaphore(1);

        ExecutorService pool = new ThreadPoolExecutor(3, 3, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(5));
        // 提交一个任务
        pool.submit(() -> {
            try {
                // 获取许可,获取不到将阻塞
                lock.acquire();
                System.out.println(res);
                // 释放许可
                lock.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        pool.shutdown();
    }
}
