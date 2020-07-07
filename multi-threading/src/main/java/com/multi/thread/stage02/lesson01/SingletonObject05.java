package com.multi.thread.stage02.lesson01;

/**
 * @Description: 单例模式
 * @Author: siteFounder
 */
public class SingletonObject05 {

    /**
     * double check
     * 相比为细粒度锁，保证单例、懒加载、性能
     * volatile 关键字保证可见性，有序性
     */
    private static volatile SingletonObject05 instance ;

    private SingletonObject05(){
        //----- empty ------
    }
    public static SingletonObject05 getInstance(){
        if(null==instance){
            synchronized (instance){
                if(null==instance){
                    instance=new SingletonObject05();
                }
            }
        }
        return instance;
    }

}
