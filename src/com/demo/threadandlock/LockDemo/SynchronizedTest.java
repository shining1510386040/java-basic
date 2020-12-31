package com.demo.threadandlock.LockDemo;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 同步锁Synchronized测试
 * @date 2020/12/31 14:36
 * @see
 */
public class SynchronizedTest {

    public static void main(String[] args) {



//        testSyncStatic();
//        testSyncNormalMethod();
//        testSyncStaticMethod();
        testSyncClass();
    }

    // 4.同步类，类锁
    private static void testSyncClass() {

        SyncThread4 one = new SyncThread4();
        SyncThread4 two = new SyncThread4();
        new Thread(one, "class-thread1").start();
        new Thread(two, "class-thread2").start();
        new Thread(new SyncThread4(), "class-thread3").start();
    }

    // 3.同步静态方法,；类锁
    private static void testSyncStaticMethod() {

        SyncThread3 one = new SyncThread3();
        SyncThread3 two = new SyncThread3();
        new Thread(one, "static-thread1").start();
        new Thread(two, "static-thread2").start();
        new Thread(new SyncThread3(), "static-thread3").start();

    }

    //2.同步非静态方法，this对象锁，互斥
    private static void testSyncNormalMethod() {
        SyncThread2 syncThread2 = new SyncThread2();
        new Thread(syncThread2, "thread1-ori").start();
        new Thread(syncThread2, "thread2-ori").start();
    }

    // 1.同步代码块，this对象锁；互斥
    private static void testSyncStatic() {
        SyncThread syncThread = new SyncThread();
        //  thread1和thread2 共用一把锁syncThread对象锁
        new Thread(syncThread, "thread1").start();
        new Thread(syncThread, "thread2").start();
    }


}
