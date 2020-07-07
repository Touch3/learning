package com.multi.thread.stage02.lesson01;

/**
 * @Description: 单例模式
 * @Author: siteFounder
 */
public class SingletonObject03 {

    /**
     * 每次取实例（已经创建完成后），都要加锁(整个方法)，影响性能
     */
    private static SingletonObject03 instance ;

    private SingletonObject03(){
        //----- empty ------
    }
    public synchronized static SingletonObject03 getInstance(){
        if(null==instance){
            instance=new SingletonObject03();
        }
        return instance;
    }

}
