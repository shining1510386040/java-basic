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

    private volatile boolean flag;

    private Object lock;

    public PrintB(boolean flag, Object lock) {
        this.flag = flag;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                try {
                    if (!flag) {
                        System.out.print("B");
                        flag = true;
                        lock.notifyAll();
                    } else {
                        lock.wait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
