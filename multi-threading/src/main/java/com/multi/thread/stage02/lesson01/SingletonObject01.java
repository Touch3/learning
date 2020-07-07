package com.multi.thread.stage02.lesson01;

/**
 * @Description: 单例模式
 * @Author: siteFounder
 */
public class SingletonObject01 {

    /**
     * can't lazy load
     */
    private static final SingletonObject01 instance =new SingletonObject01();

    private SingletonObject01(){
        //----- empty ------
    }
    public static SingletonObject01 getInstance(){
        return instance;
    }

}
