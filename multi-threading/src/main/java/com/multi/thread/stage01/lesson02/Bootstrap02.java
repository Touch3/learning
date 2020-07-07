package com.multi.thread.stage01.lesson02;

import java.util.Arrays;

/**
 * @Description: 描述 Thread 的几个构造器
 *        init(ThreadGroup g, Runnable target, String name,long stackSize)
 * @Author: siteFounder
 */
public class Bootstrap02 {
    public static void main(String[ ] args) {

        /**
         * 第一种无参构造器：init(null, null, "Thread-" + nextThreadNum(), 0)
         * 原型为： init(ThreadGroup g, Runnable target, String name,long stackSize)
         * 当创建无参构造器的线程 并启动时，当前线程不会执行内部逻辑分析源码：
         *     public void run() {
         *         if (target != null) {
         *             target.run();
         *         }
         *     }
         *   当Runnable target 为空时，不会调用run方法；当target不为空时，可以执行内部逻辑单元。
         *   复写@Override时 才会执行
         */
        Thread t1 =new Thread();
        t1.start();
        System.out.println(t1.getName());

        /**
         * Thread(Runnable target,String ThreadName)
         */
        new Thread(() ->{
            System.out.println("----");
          },"ThreadName"
        ).start();

        /**
         * init(ThreadGroup g, Runnable target, String name,long stackSize)
         * 当前 ThreadGroup 为空时，会将父线程的 ThreadGroup 作为自己的 ThreadGroup。
         * 自己、父线程处于同一个 ThreadGroup 中
         * stackSize 表示构造该线程时所需要 stack 的大小，没指定时默认为0，此时该参数会被虚拟机调用；
         * stackSize 参数在有一些平台有效，在另一些平台无效。
         */
        ThreadGroup threadGroup=Thread.currentThread().getThreadGroup();
        System.out.println(threadGroup);
        System.out.println(threadGroup.activeCount()); //3
        Thread[] threads =new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        /*
            Thread[main,5,main]
            Thread[Monitor Ctrl-Break,5,main]
            Thread[ThreadName,5,]
         */
        Arrays.asList(threads).forEach(System.out ::println);


    }
}
