package com.demo.threadandlock.LockDemo;

import java.util.concurrent.TimeUnit;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 同步对象
 * @date 2020/12/31 14:29
 * @see
 */
public class SyncThread4 extends Thread {

    // 资源
    private static int num;

    static {
        // 初始化为1
        num = 1;
    }

    public SyncThread4() {

    }


    @Override
    public void run() {
        process();
    }

    public void process() {

        synchronized (SyncThread4.class) {

        }
        // object类和子类拥有同一把锁
        synchronized (Object.class) {
            try {
                TimeUnit.SECONDS.sleep(2);
                int temp = num + 1;
                num = temp;
                for (int i = 0; i < 5; i++) {
                    num += i;
                    System.out.println(Thread.currentThread().getName() + "当前num：" + getNum());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getNum() {
        return num;
    }
}
