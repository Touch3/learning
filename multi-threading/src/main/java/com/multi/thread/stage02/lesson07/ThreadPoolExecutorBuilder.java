package com.multi.thread.stage02.lesson07;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: siteFounder
 */
public class ThreadPoolExecutorBuilder {

    public static void main(String[] args) {

        ThreadPoolExecutor executorService = (ThreadPoolExecutor)buildThreadPoolExecutor();

        int activeSize = -1;
        int queueSize = -1;
        while (true){
            if(activeSize!=executorService.getActiveCount()||queueSize!=executorService.getQueue().size()){
                System.out.println("activeSize: " + executorService.getActiveCount());
                System.out.println("queueSize: " + executorService.getQueue().size());
                System.out.println("coreSize: "+executorService.getCorePoolSize());
                System.out.println("maxSize: " + executorService.getMaximumPoolSize());
                activeSize=executorService.getActiveCount();
                queueSize=executorService.getQueue().size();
            }
        }
    }



    private static ExecutorService buildThreadPoolExecutor(){
        /**
         * @param corePoolSize the number of threads to keep in the pool, even
         *        if they are idle, unless {@code allowCoreThreadTimeOut} is set
         * @param maximumPoolSize the maximum number of threads to allow in the
         *        pool
         * @param keepAliveTime when the number of threads is greater than
         *        the core, this is the maximum time that excess idle threads
         *        will wait for new tasks before terminating.
         * @param unit the time unit for the {@code keepAliveTime} argument
         * @param workQueue the queue to use for holding tasks before they are
         *        executed.  This queue will hold only the {@code Runnable}
         *        tasks submitted by the {@code execute} method.
         * @param threadFactory the factory to use when the executor
         *        creates a new thread
         * @param handler the handler to use when execution is blocked
         *        because the thread bounds and queue capacities are reached
         */
        ExecutorService executorService = new ThreadPoolExecutor(1,3,30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3),
                r ->{
                    Thread t =new Thread(r);
                    return t;
                },new ThreadPoolExecutor.AbortPolicy());
        System.out.println("The ThreadPoolExecutor create done !");

        /**
         * executorService 提交任务后，首先将任务提交到 BlockedQueue 中，线程池首先创建 corePoolSize 个
         * 线程去执行任务，无法被执行的任务被 blocked 在 BlockedQueue 中，当 BlockedQueue 中任务已经装满时，
         * 则会扩充 corePoolSize 线程个数至 maximumPoolSize 个数去执行任务；当此时依旧无法装下任务时，则会被
         * 拒绝策略处理。
         */
        //创建的任务数
        executorService.submit(()->sleepSeconds(100));
        executorService.submit(()->sleepSeconds(10));
        executorService.submit(()->sleepSeconds(10));
        executorService.submit(()->sleepSeconds(10));
        executorService.submit(()->sleepSeconds(10));
        executorService.submit(()->sleepSeconds(10));

        return executorService;
    }

    /**
     * 模拟线程执行任务
     * @param second
     */
    private static void sleepSeconds(long second){
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
