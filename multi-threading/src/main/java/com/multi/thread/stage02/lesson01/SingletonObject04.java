package com.multi.thread.stage02.lesson01;

/**
 * @Description: 单例模式
 * @Author: siteFounder
 */
public class SingletonObject04 {

    /**
     * double check
     * 相比为细粒度锁，保证单例、懒加载、性能，但是可能出现空指针异常（对象过程初始化 未完成，重排序导致）
     */
    private static SingletonObject04 instance ;

    private SingletonObject04(){
        //----- empty ------
    }
    public static SingletonObject04 getInstance(){
        if(null==instance){
            synchronized (instance){
                if(null==instance){
                    instance=new SingletonObject04();
                }
            }
        }
        return instance;
    }

}
