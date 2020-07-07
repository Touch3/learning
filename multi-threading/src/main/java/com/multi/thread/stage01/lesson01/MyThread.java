package com.multi.thread.stage01.lesson01;

/**
 * @Description: 线程创建的第一种方式，继承 Thread 类
 * @Author: siteFounder
 */
public class MyThread extends Thread {

    private static int index = 1;
    private static final int MAX =100;

    @Override
    public void run() {
        while (index<=MAX){
            System.out.println(Thread.currentThread().getName()+":"+index++);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
