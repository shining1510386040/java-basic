package com.demo.threadandlock.newcodePritice.orderprint;

import java.util.concurrent.CountDownLatch;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description TODO
 * @date 2020/12/31 16:48
 * @see
 */
public class MainTest {

    public static void main(String[] args) throws InterruptedException {

        test1();
//        test2();

    }

    private static void test2() {

        PrintA printA = new PrintA();
        PrintB printB = new PrintB();
        new Thread(printA).start();
        new Thread(printB).start();

    }

    private static void test1() {
//        CountDownLatch latch = new CountDownLatch(1);
//        // 则塞
//        new Thread(new AThread(latch)).start();
//        new Thread(new BThread(latch)).start();
//
//        System.out.println("Main-thread...working ...");
    }
}
