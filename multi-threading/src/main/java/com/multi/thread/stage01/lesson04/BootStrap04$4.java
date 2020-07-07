package com.multi.thread.stage01.lesson04;

/**
 * @Description: 演示死锁 deadlock
 * 当多个线程互相占有对方需要的锁，而使得锁都无法得到释放时，会导致死锁。
 * 原因：在调用方法时，方法以及被调用的方法层层加锁，使得锁相互占有。
 * @Author: siteFounder
 */
public class BootStrap04$4 {
    public static void main(String[] args) {

        Lock02 l2 =new Lock02();
        Lock01 l1=new Lock01();
        l2.setLock01(l1);
        l1.setLock02(l2);

        /**
         * 两个线程并发进行
         * 线程1 依次获取 lock1 与 lock2
         * 线程2 依次获取 lock2 与 lock1
         * 当线程1 和 线程2 在分别等待lock2 与 lock1的过程中，对应的锁又被对方锁持有，
         * 于是陷入相互等待状态，形成死锁。
         */
        new Thread(()->{
            while (true)
            l1.m1();
        }
        ).start();

        new Thread(()->{
            while (true)
                l2.s2();
        }
        ).start();
    }
}

class Lock01{

    static final Object o =new Object();
    private Lock02 lock02;

    public void setLock02(Lock02 lock02) {
        this.lock02 = lock02;
    }

    public void m1(){
        synchronized (o){
            System.out.println("--m1--");
            lock02.s1();
            System.out.println();
        }
    }

    public void m2(){
        synchronized (o){
            System.out.println("--m2--");
        }
    }
}

class Lock02{

    static final Object obj =new Object();
    private Lock01 lock01;

    public void setLock01(Lock01 lock01) {
        this.lock01 = lock01;
    }

    public void s1(){
        synchronized (obj){
            System.out.println("--m1--");
        }
    }

    public void s2(){
        synchronized (obj){
            System.out.println("--m2--");
            lock01.m2();
        }
    }
}
