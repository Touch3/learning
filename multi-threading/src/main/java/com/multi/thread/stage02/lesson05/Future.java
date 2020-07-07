package com.multi.thread.stage02.lesson05;

/**
 * 异步结果集
 * @param <T>
 */
public interface Future<T> {

    /**
     * 通过调用 Future 的 get() 方法可以获得相应的结果集
     * @return
     * @throws InterruptedException
     */
    T get() throws InterruptedException;
}
