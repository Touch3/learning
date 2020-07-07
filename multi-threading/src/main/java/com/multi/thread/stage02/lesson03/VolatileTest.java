package com.multi.thread.stage02.lesson03;

/**
 * @Description:
 *
 * 当 cpu 写入数据的时候，如果发现该变量是共享变量，则会发出一个信号，通知其他cpu 中对于该变量的缓存无效；
 * 当其他 cpu 访问该变量时，会自动到主存中获取；
 *
 * 线程的三大特性：
 * 1.原子性：对基本数据的读取与赋值是原子性的；
 *   a = 10; (b=a,a++,a=a+1 等是不保证原子性的，它是多个原子操作)
 * 2.可见性：使用 volatile 保证可见性
 * 3.有序性（顺序性） happen-before relationship
 *   # 代码执行：编写在前面的 before 编写在后面的(但线程最终一致性)
 *   # lock before unlock
 *   # volatile 修饰的变量， write before read（多线程操作时）
 *   # 传递规则：A before B，B before C -> A before C
 *   # 同一个线程： 方法 start before run
 *   # 同一个线程 : interrupt before Exception
 *
 * Volatile 关键字：
 *  # 保证不同线程间的可见性；禁止重排序（有序性）；不保证原子性；
 *  # 保证对缓存的修改操作立马写入住存，同时让其他cpu对于中缓存的值无效；
 *
 * @Author: siteFounder
 */
public class VolatileTest {

    private  static final int MAX_VALUE = 50;
    /* 共享变量 */
    private volatile static int INIT_VALUE = 0;

    public static void main(String[] args) {

        new Thread(()->{
            //每一次会记录上一次线程执行到此处的 localValue 的值
            int localValue =INIT_VALUE;
            while (localValue<MAX_VALUE){
                if(localValue!=INIT_VALUE){ //此时localValue未被更新，而INIT_VALUE已经被更新了
                    System.out.println(">>> the value updated to "+INIT_VALUE+" >>>");
                    localValue=INIT_VALUE;
                }
            }
        }, "READER").start();

        new Thread(()->{
            int localValue =INIT_VALUE;
            while (localValue<MAX_VALUE){
                    System.out.println("> updating the value to "+ ++localValue);
                    INIT_VALUE = localValue;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "WRITER").start();
    }
}
