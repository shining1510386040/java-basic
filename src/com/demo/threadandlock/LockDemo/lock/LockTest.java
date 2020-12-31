package com.demo.threadandlock.LockDemo.lock;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description Lock接口的锁测试
 * @date 2020/12/31 15:17
 * @see
 */
public class LockTest {

    public static void main(String[] args) {


        testReentrantLock();

    }

    private static void testReentrantLock() {

        ReetrantLockThread reetrantLockThread = new ReetrantLockThread();
        new Thread(reetrantLockThread, "非公平锁1").start();
        new Thread(reetrantLockThread, "非公平锁2").start();
    }
}
