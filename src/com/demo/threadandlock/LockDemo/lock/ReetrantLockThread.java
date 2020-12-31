package com.demo.threadandlock.LockDemo.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 可重入锁
 * @date 2020/12/31 15:33
 * @see
 */
public class ReetrantLockThread extends Thread {

    // 非公平锁
    private ReentrantLock lock = new ReentrantLock();

    // 资源
    private Object data;

    @Override
    public void run() {

        // 加锁
        lock.lock();
        try {
            TimeUnit.SECONDS.sleep(3);
            // do some work...
            working();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    private void working() throws InterruptedException {

        //如果已经被lock,则立即返回false不会等待,达到忽略操作的效果
        if (lock.tryLock()) {
            try {
                System.out.println("I am workiing..." + Thread.currentThread().getName());
            } finally {
                lock.unlock();
            }
        }
        // 如果已经被lock,尝试等待5s,看是否可以获得锁,如果5s后仍然无法获得锁则返回false继续执行
        if (lock.tryLock(5, TimeUnit.SECONDS)) {
            try {
                System.out.println("I am workiing..." + Thread.currentThread().getName());
            } finally {
                lock.unlock();
            }

        }
    }
}
