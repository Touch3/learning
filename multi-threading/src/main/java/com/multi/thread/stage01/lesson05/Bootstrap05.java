package com.multi.thread.stage01.lesson05;

/**
 * @Description: 线程间的通信(只适合与两个线程)
 * @Author: siteFounder
 */
public class Bootstrap05 {

    private static final Object LOCK =new Object();
    private int i = 1;
    private boolean isProduced =false;  // 标签

    /**
     * 线程间通信：数据生产者
     */
    public void producer(){
        synchronized (LOCK){
            if(isProduced){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                System.out.println("P> " + i++);
                isProduced=true;
                LOCK.notify();
            }
        }
    }

    /**
     * 线程间通信：数据消费者
     */
    public void consumer(){
        synchronized (LOCK){
            if(isProduced){
                System.out.println("C> " + i);
                LOCK.notify();
                isProduced=false;
            }else {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 测试时，当为生产者 和 消费者 都只创建一个线程来执行时，在一个实例间通信时是没有问题的。（notify的只能是另一个线程）
     * 当为生产者 和 消费者 创建多个线程时，会出现问题：比如当consumer 和 producer在执行完任务后会进入wait状态与notify
     * 状态，处于 notify 状态的实例锁 只能同时由一个线程拥有。当一个线程执行consumer 和 producer任务时，notify的是执行自身
     * 方法的任务时，会使得所有锁都进入 wait 状态，无法唤醒。
     * 此时不是死锁状态，而是所有锁都进入 wait 等待状态。
     * @param args
     */
    public static void main(String[] args) {
        Bootstrap05 bootstrap05=new Bootstrap05();

        new Thread(()->{
            while (true) {
                bootstrap05.producer();
            }
         }).start();

        new Thread(()->{
            while (true) {
                bootstrap05.consumer();
            }
        }).start();

    }


}
