package com.multi.thread. stage02.lesson05;

import java.util.function.Consumer;

/**
 * @Description:
 * @Author: siteFounder
 */
public class FutureService {
    public <T> Future<T> submit(final FutureTask<T> task, Consumer<T> consumer){
        SyncFuture<T> syncFuture =new SyncFuture<>();
        new Thread(()->{
            T result = task.call();  //传进任务重新call()方法，此处执行call()
            syncFuture.process(result);
            consumer.accept(result);
        }).start();
        return syncFuture;
    }
}
