package com.demo.threaddesign;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 主进程：接收任务，分配任务，收集结果集
 * @date 2021/4/25 12:22
 * @see
 */
public class Master {

    /**
     * 任务集合
     */
    private ConcurrentLinkedQueue<Task> taskQueue = new ConcurrentLinkedQueue<>();

    /**
     * 所有的处理结果
     */
    private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<>();

    /**
     * 所有的Worker集合
     */
    private HashMap<String, Thread> workerMap = new HashMap<>();

    /**
     * 构造方法，初始化Worker
     */
    public Master(Worker worker, int workerCount) {
        //每一个worker对象都需要有Master的引用，taskQueue用于任务的提取，resultMap用于任务的提交
        worker.setTaskQueue(this.taskQueue);
        worker.setResultMap(this.resultMap);
        for (int i = 0; i < workerCount; i++) {
            //key表示worker的名字,value表示线程执行对象
            workerMap.put("worker" + i, new Thread(worker));
        }
    }

    /**
     * 用于提交任务
     */
    public void submit(Task task) {
        this.taskQueue.add(task);
    }

    /**
     * 执行方法，启动应用程序让所有的Worker工作
     */
    public void execute() {
        for (Map.Entry<String, Thread> me : workerMap.entrySet()) {
            me.getValue().start();
        }
    }

    /**
     * 判断所有的线程是否都完成任务
     */
    public boolean isComplete() {
        for (Map.Entry<String, Thread> me : workerMap.entrySet()) {
            if (me.getValue().getState() != Thread.State.TERMINATED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 总结归纳
     */
    public int getResult() {

        // todo ...

        int ret = 0;
        for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
            ret += (Integer) entry.getValue();
        }
        return ret;
    }
}
