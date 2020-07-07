package com.multi.thread.stage01.lesson06;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

/**
 * @Description:
 * @Author: siteFounder
 */
public class LockImpl implements Lock {

    /*initValue 为 true 时表示 锁已经被获取，false 时表示已经锁处于空闲状态*/
    private boolean isLocked;

    /* 存放未获得锁，处于 blocked 状态的线程 */
    Collection<Thread> blockedThreads = new ArrayList<>();

    /*保证加锁对象与解锁对象是同一个对象*/
    private Thread currentThread;

    public LockImpl() {
        this.isLocked =false;
    }

    @Override
    public synchronized void lock() throws InterruptedException {
        while (isLocked){
            blockedThreads.add(Thread.currentThread());
            this.wait();
        }
        blockedThreads.remove(Thread.currentThread());
        this.isLocked =true;
        currentThread=Thread.currentThread();
    }

    @Override
    public synchronized void unlock() throws InterruptedException, TimeOutExceptipn {
        if(currentThread==Thread.currentThread()){
            this.isLocked =false;
            System.out.println(Thread.currentThread().getName()+" release the lock .");
            this.notifyAll();
        }
    }

    @Override
    public synchronized Collection<Thread> getBlockedThreads() {
        return Collections.unmodifiableCollection(blockedThreads);
    }

    /**
     * 模拟 加锁（抢锁）、工作、释放锁 过程
     * @param args
     */
    public static void main(String[] args) {

        final LockImpl lock = new LockImpl();

        Stream.of("T1","T2","T3","T4").forEach(
                name -> new Thread(()->{
                    try {
                        lock.lock();
                        System.out.println(Thread.currentThread().getName()+" get the lock .blocking Threads ");
                        work();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            lock.unlock();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (TimeOutExceptipn timeOutExceptipn) {
                            timeOutExceptipn.printStackTrace();
                        }
                    }

                }).start()
        );
    }

    private static void work() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() +" start working ---");
        Thread.sleep(2000);
    }
}
