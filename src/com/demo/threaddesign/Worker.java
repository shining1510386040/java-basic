package com.demo.threaddesign;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 工作线程：实现具体实现
 * @date 2021/4/25 12:24
 * @see
 */
public class Worker implements Runnable {

    private ConcurrentLinkedQueue<Task> taskQueue;
    private ConcurrentHashMap<String, Object> resultMap;

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 设置任务队列
     * @date 2021/4/25 17:02
     */
    public void setTaskQueue(ConcurrentLinkedQueue<Task> taskQueue) {
        this.taskQueue = taskQueue;
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 设置结果集
     * @date 2021/4/25 17:03
     */
    public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    @Override
    public void run() {
        while (true) {
            Task executeTask = this.taskQueue.poll();
            if (executeTask == null) {
                break;
            }
            //真正的任务处理
            Object result = handle(executeTask);
            this.resultMap.put(executeTask.getName(), result);
        }
    }

    /**
     * 核心处理逻辑，可以抽离出来由具体子类实现
     */
    protected Object handle(Task executeTask) {
        Object result = null;
        try {
            //表示处理任务的耗时....
            Thread.sleep(500);
            result = executeTask.getPrice();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

}
