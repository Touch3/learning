package com.multi.thread.stage01.lesson02;

import java.util.Optional;

/**
 * @Description: 线程的一些 API 使用
 * @Author: siteFounder
 */
public class Bootstrap02$1 {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 =new Thread(() ->{
            try {
                Thread.sleep(1_000);
                System.out.println(Thread.currentThread()); //Thread[t2,5,main]
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");

        Thread t2 =new Thread(() ->{
            try {
                Thread.sleep(1_000);
                System.out.println(Thread.currentThread());  //Thread[t2,5,main]
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2");

        t1.start();
        t2.start();

        /**
         * t1,t2 的join()方法会使得main线程处于等待状态，知道t1,t2执行完毕才可运行执行线程
         *场景：任务执行先后顺序场景
         */
        t1.join();
        t2.join();

        Optional.of(Thread.currentThread()).ifPresent(System.out::println);  // Thread[main,5,main]

        /**
         * 程序一直运行，不会停止
         */
        //Thread.currentThread().join();
    }
}
