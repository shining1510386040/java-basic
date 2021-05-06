package com.demo.threaddesign;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试
 * @date 2021/4/25 12:25
 * @see
 */
public class MainTest {

    public static void main(String[] args) {
//        //实际开发中多少个线程最好写成Runtime.getRuntime().availableProcessors()
//        Master master = new Master(new Worker(), 10);
//        Random random = new Random();
//        for (int i = 0; i <= 100; i++) {
//            Task task = new Task();
//            task.setId(i);
//            task.setName("任务" + i);
//            task.setPrice(random.nextInt(1000));
//            master.submit(task);
//        }
//        master.execute();
//        long start = System.currentTimeMillis();
//        while (true) {
//            if (master.isComplete()) {
//                long end = System.currentTimeMillis() - start;
//                int ret = master.getResult();
//                System.out.println("计算结果:" + ret + ",执行耗时:" + end);
//                break;
//            }
//        }
//
//
        List<Map<String, Object>> ticketList = crawerTicket();
    }

    private static List<Map<String, Object>> crawerTicket() {

        // todo ...扒票
        // 分页获取数据  前100页数据  http调用






        return null;
    }

}
