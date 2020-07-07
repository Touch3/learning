package com.multi.thread.stage02.lesson05;

/**
 * @Description: 异步 Future, 定义 get() 获取获取的结果
 * @Author: siteFounder
 */
public class SyncFuture<T> implements Future<T> {

    // 判断当前结果是否处理完（决定 get()方法 是否返回结果）
    private volatile boolean done =false;

    private T result;

    /**
     *  做结果处理
     * @param result
     */
    public void process(T result){
        synchronized (this) {
            this.result = result;
            this.done = true;
            this.notifyAll();
        }
    }

    @Override
    public T get() throws InterruptedException {
        synchronized (this){
            if(!done){
                this.wait();
            }
        }
        return result;
    }
}
