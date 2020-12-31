package com.demo.threadandlock.newcodePritice.orderprint;

import com.demo.lambadaDemo.LambadaDemo;

import java.util.concurrent.CountDownLatch;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description TODO
 * @date 2020/12/31 16:47
 * @see
 */
public class BThread implements Runnable {

    private CountDownLatch latch;

    public BThread(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.print("B");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        }
    }
}
