package com.demo.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.*;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description Lock 接口锁测试
 * @date 2021/2/9 14:25
 * @see
 */
public class LockTest {

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Semaphore semaphore = new Semaphore(3);
        Condition condition = lock.newCondition();
        // todo...
    }
}
