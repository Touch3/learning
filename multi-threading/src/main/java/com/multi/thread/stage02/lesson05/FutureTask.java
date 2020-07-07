package com.multi.thread.stage02.lesson05;

/**
 * 任务处理接口（Runnable），相当于 Callable 接口，
 *  作物执行任务的单元，重写 call() 方法
 * @param <T>
 */
public interface FutureTask<T> {

    T call();
}
