package com.multi.thread.stage02.lesson07;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Description: 创建 cpu 个线程，执行任务完毕后自动销毁，线程池自动关闭
 * @Author: siteFounder
 */
public class ExecutorsExample02 {
    public static void main(String[] args) throws InterruptedException {
        /*
         * public static ExecutorService newWorkStealingPool() {
         * return new ForkJoinPool
         *  (Runtime.getRuntime().availableProcessors(), // 线程核数 Runtime.getRuntime().availableProcessors()
         *    ForkJoinPool.defaultForkJoinWorkerThreadFactory,
         *    null, true);
         *   }
         */
        ExecutorService executorService = Executors.newWorkStealingPool();

        // 创建 批量 callable 任务列表
        List<Callable<String>> callableList = IntStream.range(0,17).boxed().map(i->
                                                (Callable<String>)()->{
                                                    System.out.println(Thread.currentThread().getName()); //ForkJoinPool-1-worker-0
                                                    sleep(3);
                                                    return "Callable-Task-"+i;
                                                }).collect(Collectors.toList());
//        List<Future<String>> futureList = executorService.invokeAll(callableList);
        executorService.invokeAll(callableList).stream().map(future->{
            try {
                return future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }).forEach(System.out::println);

    }

    /**
     * 模拟任务耗时场景
     * @param second
     */
    private static void sleep(long second){
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
