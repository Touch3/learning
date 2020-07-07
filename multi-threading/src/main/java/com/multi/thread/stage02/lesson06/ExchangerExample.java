package com.multi.thread.stage02.lesson06;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Description:  Exchanger 的使用,在 【 两个线程 】间做信息交换
 *
 * V r =exchanger.exchange(V v)
 *    v : 本线程待交换的信息
 *    r : 另一个线程待交换的信息
 *
 * 双方线程如果未达到 exchange point,则线程会处于 block 状态；
 * 传递的值 与 接收到值是同一个值，而不是拷贝出来的。
 *
 * @Author: siteFounder
 */
public class ExchangerExample {

    public static void main(String[] args) {

        //Exchanger<V> exchanger = new Exchanger<>();  // V 的类型为待传递的对象的类型 
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+ "  start !");
            try {
                // 超时设置，超过指定时间未进行信息交换 则抛出超时异常
                String res = exchanger.exchange(" --- A ---",2, TimeUnit.SECONDS); // res = "--- B ---"
                System.out.println(Thread.currentThread().getName()+" get Value [[ "+res+"]]");
            } catch (InterruptedException | TimeoutException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+ "  end !");
        },"AAA").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+ "  start !");
            try {
                //Thread.sleep(3000);  //若其中一个休眠，则双方都等到休眠后再进行信息交换，超时则抛异常（java.util.concurrent.TimeoutException）
                String res = exchanger.exchange(" --- B ---"); // res = "--- A ---"
                System.out.println(Thread.currentThread().getName()+" get Value [[ "+res+"]]");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+ "  end !");
        },"BBB").start();

        /**
         * AAA  start !
         * BBB  start !
         * BBB get Value [[  --- A ---]]
         * BBB  end !
         * AAA get Value [[  --- B ---]]
         * AAA  end !
         */
    }

}
