package com.multi.thread.stage02.lesson07;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Description:
 * @Author: siteFounder
 */
public class ThreadPoolExecutorTask {

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(10,20,30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                r ->{
                    Thread t =new Thread(r);
                    return t;
                },new ThreadPoolExecutor.AbortPolicy());

        IntStream.rangeClosed(0,20).forEach(i->
                        executorService.submit(()->{
                            try {
                                TimeUnit.SECONDS.sleep(10);
                                System.out.println(Thread.currentThread().getName()+" 任务数为 [ "+i+" ] ");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        })
                );

        /*
         * 任务执行完毕后关闭线程池（任务未执行完时等待执行完毕）
         *
         * 20 threads to execute , 10 finished in idle state and 10 in running ,then if shutdown() ,then
         * 1. waiting 10 threads to execute till finished or interrupted ;
         * 2. interrupt 20 threads(10 idle first,then last 10)
         * 3. 20 threads will exist
         */
//        executorService.shutdown();


        /*
         *shutdownNow()
         * 1. return a runnableList remain 10 unhandled runnable
         * 2. interrupt all of threads   in the pool
         * throw Exception: java.lang.InterruptedException: sleep interrupted
         */
        List<Runnable> runnableList = executorService.shutdownNow();

        System.out.println("after shutdownNow() ,remain 【 " + runnableList.size() + " 】unhandled !");

        //串行化执行其他任务 do other work
        try {
            executorService.awaitTermination(1,TimeUnit.HOURS);
        } catch (InterruptedException e) {
        }

        System.out.println("======== do other work ========");

    }
}
