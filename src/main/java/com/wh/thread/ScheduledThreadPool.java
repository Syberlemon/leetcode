package com.wh.thread;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 测试ScheduledExecutorService是否多个线程轮流执行
 * 是大黄问我的问题，他那边线程池有5个，但是执行的时候一个线程定时执行很多次后，再切换另一个去执行
 */
public class ScheduledThreadPool {

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);

        //schedule to run after sometime
        System.out.println("Current Time = " + new Date());
        for (int i = 0; i < 20; i++) {
            Thread.sleep(1000);
            WorkerThread worker = new WorkerThread("do heavy processing");
            scheduledThreadPool.schedule(worker, 10, TimeUnit.SECONDS);
        }

        //add some delay to let some threads spawn by scheduler
        Thread.sleep(30000);

        scheduledThreadPool.shutdown();
        while (!scheduledThreadPool.isTerminated()) {
            //wait for all tasks to finish
        }
        System.out.println("Finished all threads");

    }
}
