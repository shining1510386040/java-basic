package com.demo.threadandlock.LockDemo.lock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 门闩测试
 * @date 2021/1/4 10:00
 * @see
 */
public class CountDownLatchTest {


    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        test1();
    }

    //场景1： 主线程等待所有子线程执行完毕，汇总结果；
    private static void test1() throws InterruptedException, ExecutionException, TimeoutException {
        // 5 个子线程的lantch
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService threadpool = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(4));
        List<String> data = null;

        FutureTask<List<String>> subData = new FutureTask<List<String>>(() -> {
            List<String> ret = new ArrayList<>(16);
            ret.add("sub-data");
            ret.add("sub-data");
            latch.countDown();
            return ret;
        });
        threadpool.submit(subData);
        List<String> subData1 = subData.get(5,TimeUnit.SECONDS);

        // 线程2
        Future<List<String>> subData2 = threadpool.submit(() -> {
            List<String> ret = new ArrayList<>(16);
            ret.add("sub-data2");
            latch.countDown();
            return ret;
        });
        // 线程3
        Future<List<String>> subData3 = threadpool.submit(() -> {
            List<String> ret = new ArrayList<>(16);
            ret.add("sub-dat3");
            latch.countDown();
            return ret;
        });
        try {
            // 等待子线程执行完毕
            latch.await();
            data = new ArrayList<>(18);
            data.addAll(subData1);
            data.addAll(subData2.get());
            data.addAll(subData3.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(data);
        threadpool.shutdown();

    }

}
