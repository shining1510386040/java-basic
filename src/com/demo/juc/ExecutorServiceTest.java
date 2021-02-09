package com.demo.juc;

import java.util.concurrent.*;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 线程池测试
 * @date 2021/2/9 14:27
 * @see
 */
public class ExecutorServiceTest {

    public static void main(String[] args) {
        //
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        // 任务等待队列
        BlockingQueue workQueue = new LinkedBlockingQueue(5);
        ExecutorService threadPool = new ThreadPoolExecutor(3, 5, 0, TimeUnit.SECONDS, workQueue);

        threadPool.submit(()->{

        });
        // todo ...
    }
}
