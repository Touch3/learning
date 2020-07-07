package com.multi.thread.stage02.lesson01;

/**
 * @Description: 单例模式
 * @Author: siteFounder
 */
public class SingletonObject07 {

    private SingletonObject07(){}

    /**
     * 推荐的方式：通过枚举方式,在枚举中创建、初始化实例只执行一次
     */
   private enum Singleton{
       INSTANCE;
       private final SingletonObject07 instance;
       Singleton(){
           instance=new SingletonObject07();
       }
       public SingletonObject07 getInstance(){
           return instance;
       }
    }

    public SingletonObject07 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

}
