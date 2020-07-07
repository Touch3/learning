package com.multi.thread.stage02.lesson04;

/**
 * @Description: 单线程设计模式，每一时刻只允许一个线程公共资源（方法、变量等）进行操作
 * @Author: siteFounder
 */
public class Gate {

    private int counter = 0;
    private String name = "NoBody";
    private String address = "NoWhere";

    /**
     * pass 方法为共享资源，多个线程同时调用该方法时，
     * 为了避免某一时刻只能被一个线程使用，应该对本方法加锁
     * @param name
     * @param address
     */
    public synchronized void pass(String name,String address){
        counter++;
        this.name=name;
        this.address=address;
        verify();
    }

    private void verify() {
        if(this.address.charAt(0)!=this.name.charAt(0)){
            System.out.println("****BROKEN*****"+toString());
        }
    }

    public String toString(){
        return "NO "+counter+" ,name:"+name+" ,"+address;
    }
}
