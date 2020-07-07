package com.multi.thread.stage02.lesson05;

/**
 * @Description:
 *
 * Future : 结果集
 * FutureTask : 任务执行单元（callable）
 * FutureService: 桥接 Future 和 FutureTask
 *
 * @Author: siteFounder
 */
public class BootStrap {
    public static void main(String[] args) throws InterruptedException {
        FutureService futureService = new FutureService();

        // future 封装了 callable 任务执行的返回结果
        Future future=futureService.submit(()->{
            try {
                System.out.println("开始执行任务 <<<<<<");
                Thread.sleep(5000);
                System.out.println("执行任务完毕 >>>>>>");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Returning of Call ";  // 重写 call() 方法的返回值
        },System.out::println);

        System.out.println("Do other things ---");
//        System.out.println("获得任务执行结果： "+future.get());


    }
}
