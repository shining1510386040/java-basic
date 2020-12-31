package com.demo.threadandlock.LockDemo;

import java.util.concurrent.TimeUnit;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 同步代码块
 * @date 2020/12/31 14:29
 * @see
 */
public class SyncThread extends Thread {

    // 资源
    private int num;

    public SyncThread() {
        // 初始化为1
        this.num = 1;
    }

    @Override
    public void run() {
        // 加锁
        synchronized (this) {
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

    public int getNum() {
        return num;
    }
}
