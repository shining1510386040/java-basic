package com.demo.threadandlock.threadDemo;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 多线程实现第三种方式，带返回结果的；
 * 需futuretask 类
 * @date 2020/12/30 19:29
 * @see
 */
public class ThreadWay3 implements Callable<String> {

    @Override
    public String call() throws Exception {

        TimeUnit.SECONDS.sleep(2);
        System.out.println("I am sub-thread." + Thread.currentThread().getName());
        // 异常可被主线程捕获；
        int num = 1 / 0;
        return "sub result";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("I am  main-thread:" + Thread.currentThread().getName());
        // 入参为Callable接口,FutureTask的顶级接口为 Runable接口，
        FutureTask<String> task = new FutureTask<>(new ThreadWay3());
        // 开启异步线程
        new Thread(task).start();
        // 会阻塞主线程
        String ret = task.get();
        System.out.println("I am main-thread:" + new Date());
        System.out.println("working ...");
    }
}
