package com.demo.threadandlock.threadDemo;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 多线程的第二种实现方式，
 * @date 2020/12/30 19:22
 * @see
 */
public class ThreadWay2 implements Runnable {

    @Override
    public void run() {

        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("I am sub-thread:" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 测试
    public static void main(String[] args) {

        System.out.println("I am main thread..." + Thread.currentThread().getName());
        Thread thread = new Thread(new ThreadWay2());
        thread.start();

        System.out.println("I am main,workiing " + new Date());
    }
}
