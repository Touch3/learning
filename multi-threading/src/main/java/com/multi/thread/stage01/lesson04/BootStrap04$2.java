package com.multi.thread.stage01.lesson04;

/**
 * synchronized 的范围：对 synchronized 内部的逻辑由单个线程完全执行，期间不允许别的线程执行。
 * @Description: synchronized 作用于方法时，则同一个线程执行整个方法所有内容；
 * 有两种方式：1⃣️在方法中添加 synchronized ，2⃣️对方法中的所有内容 作为同步代码块。
 * @Author: siteFounder
 */
public class BootStrap04$2 {

    private static int index = 1;
    private static final int MAX =1000;

    public static void main(String[] args) {

        Runnable r = new Runnable() {
            @Override
            public  void run() {
                synchronized (this) { //同步方法中的所有内容
                    while (true) {
                        if (index > MAX)
                            break;
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + ":  " + index++);
                    }
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
