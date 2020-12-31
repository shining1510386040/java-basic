package com.demo.threadandlock.threadDemo;

import java.util.concurrent.TimeUnit;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 任务
 * @date 2020/12/31 11:33
 * @see
 */
public class WorkTask extends Thread {

    // 模拟任务处理延时
    private long time;

    public WorkTask(long time) {
        this.time = time;
    }

    @Override
    public void run() {

        try {
            TimeUnit.SECONDS.sleep(time);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
