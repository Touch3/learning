package com.multi.thread.stage01.lesson05;

import java.util.stream.Stream;

/**
 * @Description: wait 与 sleep 的四个区别
 * @Author: siteFounder
 *       【wait】        【sleep】
 * 1      Object         Thread
 * 2     需要被唤醒         不需要
 * 3    依赖monitor        不依赖
 * 4      释放锁           不释放锁
 */
public class Sleep$Wait {

    public static final Object monitor =new Object();

    /**
     * 给sleep加锁时，线程拿到时不释放锁，执行完毕后释放
     */
    public static void m1(){
        synchronized (monitor) { // 等 4s 以后才输出
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void m2(){
        synchronized (monitor){ //几乎同时输出
            try {
                System.out.println(Thread.currentThread().getName());
                monitor.wait(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        Stream.of("T1","T2").forEach(name ->
                new Thread(name){
                    @Override
                    public void run() {
//                        m1();
                        m2();
                    }
                }.start()

        );
    }

}
