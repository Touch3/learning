package com.multi.thread.stage01.lesson04;

/**
 * synchronized 的范围：对 synchronized 内部的逻辑由单个线程完全执行，期间不允许别的线程执行。
 * @Description: synchronized 同步代码块，同步一个对象 Object
 * @Author: siteFounder
 */
public class BootStrap04$1 {

    private static int index = 1;
    private static final int MAX =1000;
    private static final Object MONITOR = new Object();

    public static void main(String[] args) {

        Runnable r =()->{
            while (true){
                synchronized (MONITOR){
                    // 同步开始位置
                    if(index>MAX)
                        break;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+":  "+index++);
                    //同步结束位置
                }
            }
        };

        Thread t1 = new Thread(r,"t1");
        Thread t2 = new Thread(r,"t2");
        Thread t3 = new Thread(r,"t3");
        t1.start();
        t2.start();
        t3.start();
    }
}
