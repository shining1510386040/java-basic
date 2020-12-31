package com.demo.threadandlock.newcodePritice.orderprint;

import java.util.concurrent.CountDownLatch;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description TODO
 * @date 2020/12/31 16:47
 * @see
 */
public class AThread implements Runnable {

    private CountDownLatch latch;

    public AThread(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.print("A");
                latch.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
