package com.multi.thread.stage01.lesson01;

/**
 * @Description: 在一个main中启动了多个线程，不同交替执行
 * @Author: siteFounder
 */
public class BootStrap01 {

    private static int index = 1;
    private static final int MAX =100;

    public static void main(String[] args) {
        /**
         * 两个线程交替执行任务,导致线程不安全。
         * 读取的数据尽管只存在一份（static），但是也会出现错误数据。
         */
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        /**
         * 一个线程不能够启动两次，一个线程不能改同时处于两种状态。
         * 新建（NEW），就绪（RUNNABLE），运行（RUNNING）阻塞（BLOCKED），终止（TERMINATED）
         * 在第二次调用start()方法的时候，线程可能处于终止或者其他（非NEW）状态，是不可以再次启动的。
         * java.lang.IllegalThreadStateException
         */
//        t1.start();
//        t2.start();


        /**
         * 采用 runnable 接口创建线程
         */
        MyRunnable r = new MyRunnable();
        new Thread(r,"RunThread01").start();
        new Thread(r,"RunThread02").start();
        new Thread(r,"RunThread03").start();

        /**
         * 采用匿名内部类的方式启动线程
         */
        new Thread(){
            @Override
            public void run() {
                System.out.println("--采用匿名内部类的方式启动线程--");
            }
        }.start();

        /**
         * java8的方式 创建一个新的线程
         */
        Runnable r8  = () -> {
            // run（） 的内部逻辑
            while (index<=MAX){
                System.out.println(Thread.currentThread().getName()+"java8 :"+index++);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(r8,"java8").start();
        /**上式等价于一下内容 */
        new Thread(() -> {
                // run（） 的内部逻辑
                while (index<=MAX){
                    System.out.println(Thread.currentThread().getName()+"java8 :"+index++);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"ThreadName"
        ).start();

    }
}
