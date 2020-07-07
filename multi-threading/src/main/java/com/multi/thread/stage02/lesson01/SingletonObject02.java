package com.multi.thread.stage02.lesson01;

/**
 * @Description: 单例模式
 * @Author: siteFounder
 */
public class SingletonObject02 {

    /**
     * 无法保证线程安全，创建的不止一个实例
     */
    private static SingletonObject02 instance ;


    private SingletonObject02(){
        //----- empty ------
    }
    public static SingletonObject02 getInstance(){
        if(null==instance){
            instance=new SingletonObject02();
        }
        return instance;
    }

}
