package com.demo.threadandlock.newcodePritice.orderprint;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description
 * @date 2020/12/31 17:06
 * @see
 */
public class PrintA implements Runnable {

    private volatile boolean flag = true;

    @Override
    public void run() {
        while (true) {
            synchronized (Object.class) {
                try {
                    if (flag) {
                        System.out.print("A");
                        flag = false;
                        this.wait();
                    } else {
                        this.notify();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
