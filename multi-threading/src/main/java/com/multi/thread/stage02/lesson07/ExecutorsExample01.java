package com.multi.thread.stage02.lesson07;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Description:
 * @Author: siteFounder
 */
public class ExecutorsExample01 {

    public static void main(String[] args) {
//        useCachedThreadPool();
//        useFixedSizePool();
//        singleThreadPool();
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    /** 01
     * ----- Executors.newCachedThreadPool() -----
     * These pools will typically improve the performance
     *   of programs that execute many short-lived asynchronous tasks.
     */
    private static void useCachedThreadPool(){

        /*
         * return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
         *                                       60L, TimeUnit.SECONDS,
         *                                       new SynchronousQueue<Runnable>());
         *      A {@linkplain BlockingQueue blocking queue} in which each insert
         *      operation must wait for a corresponding remove operation by another
         *      thread, and vice versa.  A synchronous queue does not have any
         *      internal capacity, not even a capacity of one.
         * 初始线程数为 0 个，可以创建的最大线程数为 Integer.MAX_VALUE ；当提交任务时，任务首先会进入
         * SynchronousQueue（最多一个） ，然后创建线程去取任务执行，任务执行完毕后等待 60 s无任务时自
         * 动回收（销毁）线程； ActiveCount 数量会变化的；
         * 因此线程会不断的创建，然后回收，适用于单个任务执行时长较短的场景；
         */
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println("Current ActiveCount "+((ThreadPoolExecutor)executorService).getActiveCount());

        IntStream.rangeClosed(0,20).forEach(i->executorService.execute(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {}
            System.out.println(Thread.currentThread().getName()+" 为当前第 【 "+i+" 】个线程");
            System.out.println("Current ActiveCount "+((ThreadPoolExecutor)executorService).getActiveCount());
                }));
    }

    /** 02
     *  Executors.newFixedThreadPool(10)
     *  If additional tasks are submitted when all threads are active,
     *       they will wait in the queue until a thread is available.
     */
    private static void useFixedSizePool(){

        /*
         * public static ExecutorService newFixedThreadPool(int nThreads) {
         * return new ThreadPoolExecutor(nThreads, nThreads,
         *                              0L, TimeUnit.MILLISECONDS,
         *                              new LinkedBlockingQueue<Runnable>());
         *                             }
         * 核心线程数 与 最大线程数是一样的，线程开始就被创建 固定个数 ，使用后不进行回收（销毁），
         * LinkedBlockingQueue blocked 队列 最大可以放置 Integer.MAX_VALUE 个任务，
         * 每次保证固定的线程取执行。
         */
        ExecutorService executeService = Executors.newFixedThreadPool(10);
        System.out.println(" Current ActiveCount "+((ThreadPoolExecutor)executeService).getActiveCount());

        IntStream.rangeClosed(0,30).forEach(i->executeService.execute(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {}
            System.out.println(Thread.currentThread().getName()+" 为当前第 【 "+i+" 】个线程");
            System.out.println("Current ActiveCount "+((ThreadPoolExecutor)executeService).getActiveCount());
        }));
    }

    /**
     * Executors.newSingleThreadExecutor()
     * difference between one thread:
     *  1. the thread is still alive in newSingleThreadPool,but one thread not
     *  2. multi runnable will be store in unbound queue ,but one thread not
     */
    private static void singleThreadPool(){
        /*
         * public static ExecutorService newSingleThreadExecutor() {
         * return new FinalizableDelegatedExecutorService  // (ThreadPoolExecutor) cast failure
         *   (new ThreadPoolExecutor(1, 1,
         *                           0L, TimeUnit.MILLISECONDS,
         *                           new LinkedBlockingQueue<Runnable>()));
         *                    }
         * 使用一个线程来执行任务，执行完不进行回收
         */
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();

        IntStream.rangeClosed(0,5).forEach(j->executorService1.execute(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {}
            System.out.println(Thread.currentThread().getName()+" 为当前第 【 "+j+" 】个线程");
        }));
    }


}
