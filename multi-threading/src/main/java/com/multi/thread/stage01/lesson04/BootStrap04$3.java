package com.multi.thread.stage01.lesson04;

/**
 * @Description: synchronized 作用于方法
 * 理解 this 锁，作用于实例方法时，锁住的就是这个对象实例.
 *     多个线程执行该实例的 synchronized 方法时，当前线程获得该方法实例的锁，
 *     对象实例锁只能被一个线程占有，只有执行完毕时才会给其他线程执行(对于同一个对象而言)。
 *     作用于静态方法时，锁住当前的 class ,class 锁只能被一个线程占有，只有执行完毕时才会给其他线程执行。
 * 作用于静态代码块为 class 锁 ,最先被执行。
 * @Author: siteFounder
 */
public class BootStrap04$3 {

    public static void main(String[] args) {
        SyncMethod syncMethod = new SyncMethod();

        /**测试实例方法*/
        new Thread(() -> syncMethod.sync01()).start();
        new Thread(() -> syncMethod.sync02()).start();

        /**测试静态方法*/
        new Thread(() -> SyncMethod.syncStatic01()).start();
        new Thread(() -> SyncMethod.syncStatic02()).start();

        /*
         实例方法 与 静态方法 的锁对象不一样，分别为【对象实例】 和 【Class】
         */
    }
}

class SyncMethod {

    /**
     * 静态块的 class 锁
     */
    static {
        synchronized (SyncMethod.class){
            System.out.println("static block 5555");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void sync01(){
        System.out.println("111111111");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sync02(){
        System.out.println("222222222");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 静态方法测试
     */
    public static synchronized void syncStatic01(){
        System.out.println("static333333");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void syncStatic02(){
        System.out.println("static444444");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
