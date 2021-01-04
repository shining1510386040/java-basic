package com.demo.threadandlock.LockDemo.lock;

import java.util.concurrent.*;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 门闩测试
 * @date 2021/1/4 10:55
 * @see
 */
public class CoutDownLatchTest2 {

    public static void main(String[] args) throws InterruptedException {

        // 裁判
        CountDownLatch latch = new CountDownLatch(1);
        // 选手
        CountDownLatch downLatch = new CountDownLatch(5);
        ExecutorService threadPool = new ThreadPoolExecutor(5, 5, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>(3));

        for (int i = 0; i < 5; i++) {
            final int cur = i;
            threadPool.submit(() -> {
                try {
                    latch.await();
                    System.out.println("player is running..." + cur);
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("player is reach..." + cur);
                    downLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        System.out.println(" judjer work..");
        latch.countDown();
        downLatch.await();

    }


}
