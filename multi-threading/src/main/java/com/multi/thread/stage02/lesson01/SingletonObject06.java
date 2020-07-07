package com.multi.thread.stage02.lesson01;

import java.util.stream.IntStream;

/**
 * @Description: 单例模式
 * @Author: siteFounder
 */
public class SingletonObject06 {

    private SingletonObject06(){}

    /**
     * 推荐的方式：通过在静态类（只加载一次）中构造对象，在每次加载（调用 getInstance）方法时才会加载
     */
    private static class InstanceHolder{
        private static final SingletonObject06 instance = new SingletonObject06();
    }

    public static SingletonObject06 getInstance(){
        return InstanceHolder.instance;
    }



    public static void main(String[] args) {
        IntStream.range(0,40).forEach(i -> new Thread(()->{
            System.out.println(SingletonObject06.getInstance());
        }).start());
    }

}
