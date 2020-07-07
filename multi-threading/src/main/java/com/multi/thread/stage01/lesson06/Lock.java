package com.multi.thread.stage01.lesson06;

import java.util.Collection;

public interface Lock {

    class TimeOutExceptipn extends Exception{
        public TimeOutExceptipn(String msg){
            super(msg);
        }
    }

    /**
     * 线程加锁
     * @throws InterruptedException
     */
    void lock() throws InterruptedException;

    /**
     * 释放锁
     * @throws InterruptedException
     * @throws TimeOutExceptipn
     */
    void unlock() throws InterruptedException,TimeOutExceptipn;

    /**
     * 获取 blocked 队列
     * @return
     */
    Collection<Thread> getBlockedThreads();

}
