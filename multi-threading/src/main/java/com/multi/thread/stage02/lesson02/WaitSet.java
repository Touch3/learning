package com.multi.thread.stage02.lesson02;


import java.util.stream.IntStream;

/**
 * @Description:
 *
 * 1.所有的 Object 对象都会有一个 WaitSet，用来存放调用该对象 wait 方法而进入block状态的线程
 * 2.线程被 notify 后不一定立即进入 运行状态
 * 3.进入 waitSet 状态顺序与出 waitSet 顺序不一定是一致的
 * 4.线程被唤醒后必须重新获取锁，当前线程会记录之前有锁时执行到的位置并继续执行。
 * @Author: siteFounder
 */
public class WaitSet {

    private final static Object LOCK =new Object();

    public static void main(String[] args) throws InterruptedException {
        //模拟使得5个线程进入 block 状态
        IntStream.rangeClosed(1,5).forEach(i->
                new Thread(String.valueOf(i)){
                    @Override
                    public void run() {
                        synchronized(LOCK){
                            try {
                                System.out.println(Thread.currentThread().getName()+" come to WaitSet !");
                                LOCK.wait();
                                System.out.println(Thread.currentThread().getName()+" leave from WaitSet ------");

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start()
        );

        Thread.sleep(3000);

        //唤醒 5 个进入 block状态的线程
        IntStream.rangeClosed(1,5).forEach(i->{
            synchronized (LOCK){
                LOCK.notify();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
         }
        );
        /** 结果：
         * 1 come to WaitSet !
         * 5 come to WaitSet !
         * 4 come to WaitSet !
         * 3 come to WaitSet !
         * 2 come to WaitSet !
         * 1 leave from WaitSet ------
         * 2 leave from WaitSet ------
         * 3 leave from WaitSet ------
         * 4 leave from WaitSet ------
         * 5 leave from WaitSet ------
         */
    }

}
