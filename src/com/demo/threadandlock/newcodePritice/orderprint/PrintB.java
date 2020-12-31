package com.demo.threadandlock.newcodePritice.orderprint;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description
 * @date 2020/12/31 17:06
 * @see
 */
public class PrintB implements Runnable {

    private volatile boolean flag = false;

    @Override
    public void run() {
        while (true) {
            synchronized (Object.class) {
                try {
                    if (!flag) {
                        System.out.print("B");
                        flag = true;
                        this.wait();
                    } else {
                        this.notify();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
