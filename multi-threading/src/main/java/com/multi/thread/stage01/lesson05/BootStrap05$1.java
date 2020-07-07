package com.multi.thread.stage01.lesson05;

import java.util.stream.Stream;

/**
 * @Description:
 * @Author: siteFounder  （while notifyAll()）
 * notifyAll() 唤起所有处于 wait 状态的线程
 */
public class BootStrap05$1 {

    private static final Object LOCK =new Object();
    private int i = 1;
    private boolean isProduced =false;  // 标签

    /**
     * 线程间通信：数据生产者
     */
    public void producer(){
        synchronized (LOCK){
            while(isProduced){ //保证只有一个生产者能够生产数据，并且在消费后再生产数据（每一个生产者生产数据前都判断）
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i++;
            System.out.println("P> " + i);
            isProduced=true;
            LOCK.notifyAll();
        }
    }

    /**
     * 线程间通信：数据消费者
     */
    public void consumer(){
        synchronized (LOCK){
            while(!isProduced){//保证数据只能被消费一次，每次消费数据前都做一次判断
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("C> " + i);
            LOCK.notifyAll();
            isProduced=false;
        }
    }

    public static void main(String[] args) {
        BootStrap05$1 bootStrap05$1 =new BootStrap05$1();

        Stream.of("P1","P2","P3").forEach(n->
                new Thread(n){
                    @Override
                    public void run() {
                        while (true){
                            bootStrap05$1.producer();
                        }
                    }
                }.start()
        );

        Stream.of("C1","C2","C3","C4").forEach(n->
                new Thread(n){
                    @Override
                    public void run() {
                        while (true){
                            bootStrap05$1.consumer();
                        }
                    }
                }.start()
        );

    }

}
