package com.demo.threadandlock.threadDemo;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 多线程第一种实现方式；
 * @date 2020/12/30 19:11
 * @see
 */
public class ThreadWay1 extends Thread {

    @Override
    public void run() {

        try {
            TimeUnit.SECONDS.sleep(2);
            // 线程工作单元。。do some work。。
            System.out.println("I am sub-thread ....: " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("发生异常" + e.getLocalizedMessage());
            // 线程中异常抛出，主线程正常方式也捕获不到
        }


    }

    // 测试
    public static void main(String[] args) {

        System.out.println("I am main-thread..." + Thread
                .currentThread().getName());

        ThreadWay1 threadWay1 = new ThreadWay1();
        // 开启线程threadWay1，异步执行。。
        threadWay1.start();

        System.out.println("I am main ,working.." + new Date());

    }
}
