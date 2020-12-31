package com.demo.threadandlock.threadDemo.pool;

import com.demo.threadandlock.threadDemo.ThreadWay1;
import com.demo.threadandlock.threadDemo.ThreadWay2;
import com.demo.threadandlock.threadDemo.ThreadWay3;
import com.demo.threadandlock.threadDemo.WorkTask;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.sun.org.apache.regexp.internal.RE;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.concurrent.*;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 线程池接口：ExecutorService
 * 创建方式：使用new ThreadPoolExecutor（）
 * 而不是 Executors 工具类，原因：容易产生oom
 * @date 2020/12/30 19:48
 * @see
 */
public class ThreadPoolTest {


    public static void main(String[] args) {

//        testExecutorService();

//        testfixedThreadPool();
//        testscheduledThreadPool();
//        testcacheThreadPool();
//        testsingleTreadPool();
        // 任务窃取算法
        testworkStealPool();
    }

    // 本质为ForkJoinPool ，用到分而治之，递归计算等。。
    private static void testworkStealPool() {
        ExecutorService workStealingPool = Executors.newWorkStealingPool();
        // 获取的是cpu核心线程数也就是计算资源。
        // cpu密集型计算推荐设置线程池核心线程数为N，也就是和cpu的线程数相同，
        // 可以尽可能低避免线程间上下文切换。
        // io密集型计算推荐设置线程池核心线程数为2N，但是这个数一般根据业务压测出来的，
        // 如果不涉及业务就使用推荐。Runtime.getRuntime().availableProcessors()方法询问jvm，
        // jvm去问操作系统，操作系统去问硬件。。。。。。。

        // 获取当前运行机器的核心线程数；（cpu可能会超频。）
        System.out.println(Runtime.getRuntime().availableProcessors());

        workStealingPool.submit(new WorkTask(1L));
        workStealingPool.submit(new WorkTask(2L));
        workStealingPool.submit(new WorkTask(2L));
        workStealingPool.submit(new WorkTask(2L));
        workStealingPool.submit(new WorkTask(2L));
        workStealingPool.submit(new WorkTask(2L));
        workStealingPool.submit(new WorkTask(2L));
        workStealingPool.submit(new WorkTask(2L));
        workStealingPool.submit(new WorkTask(2L));
        workStealingPool.submit(new WorkTask(2L));
        workStealingPool.submit(new WorkTask(2L));
        workStealingPool.submit(new WorkTask(2L));
        workStealingPool.submit(new WorkTask(2L));

        try {
            //由于产生的是精灵线程（守护线程、后台线程），主程序不阻塞的话看不到打印信息
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 一个线程数的线程池，相当于串行执行任务
    private static void testsingleTreadPool() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        Future<String> submit = singleThreadExecutor.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "first");
            return "first";
        });
        Future<String> submit2 = singleThreadExecutor.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "second");
            return "second";
        });
        singleThreadExecutor.shutdown();

    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 在向线程池提交任务（任务是runable、Callable接口的实现）的时候，
     * 线程池 会提供一个线程（要么创建，使用核心线程）来异步执行任务，
     * @date 2020/12/31 9:51
     */
    private static void testExecutorService() {

        // 不要用Executors工具类创建线程池；
        // 固定线程数量的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        // 要手动创建 new ThreadPoolExecutor()
        // 核心线程数，最大线程数，任务等待队列
        ExecutorService poolExecutor = new ThreadPoolExecutor(1,
                3, 0, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1));

        // 提交一个任务
        poolExecutor.submit(new ThreadWay1());
//        poolExecutor.submit(new ThreadWay1());
//        poolExecutor.submit(new ThreadWay1());
//        poolExecutor.submit(new ThreadWay1());
        // 当提交的任务数大于 最大线程数+任务等待队列时 会进入拒绝策略
        poolExecutor.submit(new ThreadWay1());
        // 关闭线程池,调用此方法并不会立即关闭线程池，而是线程池不再接受任务，
        // 等待线程池中的任务执行完毕，才会关闭线程池
        poolExecutor.shutdown();

        // 关闭后，再提交任务 不会再异步执行任务,抛出异常：java.util.concurrent.RejectedExecutionException
//        poolExecutor.submit(new ThreadWay1());

    }

    // 固定线程数量的线程池
    private static void testfixedThreadPool() {

        // 1.固定线程数量的线程池：核心线程等于最大线程，
        // 任务等待队列为Integer.MAX_VALUE(2^31-1)个 ，可能会发生oom
        // JVM OOM问题一般是创建太多对象，同时GC 垃圾来不及回收导致的
        // 面试题：固定线程数量的线程池造成OOM的原因？
//       回答： newFixedThreadPool线程池的核心线程数是固定的，它使用了近乎于无界的LinkedBlockingQueue阻塞队列。
//        当核心线程用完后，任务会入队到阻塞队列，如果任务执行的时间比较长，没有释放，
//        会导致越来越多的任务堆积到阻塞队列，最后导致机器的内存使用不停的飙升，造成JVM OOM。
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        while (true) {
            fixedThreadPool.submit(new ThreadWay1());
        }


    }

    // 周期性执行任务的线程池（定时任务）
    private static void testscheduledThreadPool() {

        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
        // 任务等待队列为：延迟队列DelayedWorkQueue
        ScheduledExecutorService scheduledPool = new ScheduledThreadPoolExecutor(2);

        // 1.延迟执行：延迟2秒后执行
        scheduledThreadPool.schedule(() -> {
            System.out.println(" execute delay 2 s...");
        }, 2, TimeUnit.SECONDS);
        // 2.周期执行:延迟3秒后，每两秒周期性执行一次
        scheduledThreadPool.scheduleAtFixedRate(() -> {
            System.out.println(" execute delay 3 s,repeat every 2s");
        }, 3, 2, TimeUnit.SECONDS);

        scheduledThreadPool.shutdown();
    }

    // 带缓冲的线程池：线程池无线大，可灵活回收空闲线程
    private static void testcacheThreadPool() {
        // 原理：使用SynchronousQueue，公平模式下的队列，无界阻塞
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        // 线程池为无限大，当执行第二个任务时第一个任务已经完成，
        // 会复用执行第一个任务的线程，而不用每次新建线程。

        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                // 等待异步线程执行后，回收
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.submit(() -> {
                System.out.println("working ...。i：" + index + Thread.currentThread().getName());
            });
        }
        cachedThreadPool.shutdown();

    }


}
