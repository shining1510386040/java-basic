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

    private volatile boolean flag;

    private Object lock;

    public PrintA(boolean flag, Object lock) {
        this.flag = flag;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                try {
                    if (flag) {
                        System.out.print("A");
                        flag = false;
                        lock.notifyAll();
                    } else {
                        lock.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
