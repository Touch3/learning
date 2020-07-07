package com.multi.thread.stage01.lesson02;

/**
 * @Description: 验证 interrupt() 方法
 * 当前线程调用 object.wait() sleep() join()方法时使得处于blocked状态时，再调用interrupt()方法会抛出异常。
 * java.lang.InterruptedException: sleep interrupted
 * @Author: siteFounder
 */
public class BootStrap02$2 {

    private static final  Object o = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread t1=new Thread(()->{
            while (true){
                synchronized (o) { //java.lang.IllegalMonitorStateException
                    try {
                        //Thread.sleep(100); // java.lang.InterruptedException: sleep interrupted
                        o.wait(); //java.lang.InterruptedException
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("Thread处于 blocked 状态时，调用 interrupt()方法会抛出异常！");
                    }
                }
            }
        });
        t1.start();
        System.out.println(t1.isInterrupted());
        t1.interrupt(); //调用 interrupt()方法打断线程
        System.out.println(t1.isInterrupted());



        Thread t2=new Thread(()->{
            while (true){}
        });
        t2.start();
        Thread main =Thread.currentThread();
        Thread t3 =new Thread(()->{
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            main.interrupt(); // 打断 main 线程的方法，抛异常  java.lang.InterruptedException
            }
        );
        t3.start();
        t2.join(); //使得 main 线程处于 blocked 状态

    }
}
