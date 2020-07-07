package com.multi.thread.stage01.lesson07;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * @Description: 手写一个自定义线程池
 * 思路：
 * 1 创建 Runnable 队列，由外界提供 Runnable ，执行。
 * 2 创建 Thread 队列，即线程池队列， 存取线程 。
 * @Author: siteFounder
 */
public class SimpleThreadPool extends Thread {

    //defined size of ThreadPool
    private int recordSize;

    private int minSize;

    private int activeSize;

    private int maxSize;

    //defined size of taskQueue
    private final int taskQueueSize;

    //defined discardPolicy
    private final DiscardPolicy discardPolicy ;

    //default taskQueue size
    private final static int DEFAULT_TASK_QUEUE_SIZE = 2000;

    //default discardPolicy
    public final static DiscardPolicy DEFAULT_DISCARD_POLICY =()->{
        throw new DataFormatException("Discard this task.");
    };
    //Thread ID（incr by one）
    private static volatile int seq = 0;

    //Thread name prefix defined in ThreadPool
    private final static String THREAD_PREFIX ="THREAD_IN_POOL_";

    //ThreadGroup name for Thread in ThreadPool
    private final static ThreadGroup GROUP =new ThreadGroup("THREAD_POOl ");

    //actually Runnable waiting to run after submitted will be put into TASK_QUEUE and beNull after finished
    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    //actually Thread will be put into THREAD_QUEUE after initialization and beNull after run over
    private final static List<WorkTask> THREAD_QUEUE = new ArrayList<>();

    // 默认线程池所处状态：关闭状态
    private volatile boolean destroy = false;

    //初始化线程池的相关参数（）默认
    public SimpleThreadPool(){
        this(4,8,12,DEFAULT_TASK_QUEUE_SIZE,DEFAULT_DISCARD_POLICY );
    }

    //根据实际任务建立线程池时的相关参数
    public SimpleThreadPool(int minSize,int activeSize,int maxSize,int taskQueueSize,DiscardPolicy discardPolicy) {
        this.minSize = minSize;
        this.activeSize = activeSize;
        this.maxSize = maxSize;
        this.taskQueueSize=taskQueueSize;
        this.discardPolicy=discardPolicy;
        init();
    }

    /*初始化线程池：创建 size 个线程，启动，并放到线程池中  */
    private void init(){
        for(int i = 0; i< minSize; i++){
            createWorkTask();
        }
        this.recordSize =minSize;
        this.start();
    }

    // 初始化一个线程
    private void createWorkTask(){
         WorkTask workTask = new WorkTask(GROUP,THREAD_PREFIX + (seq++));
         workTask.start();
        THREAD_QUEUE.add(workTask);
    }

    /**
     * 对外提供：提交任务到线程池
     * @param runnable
     */
    public void submit(Runnable runnable) throws DataFormatException {
        if(destroy){ //关闭后的线程池不能够再提交任务
            throw new IllegalThreadStateException(" the thread pool already destroy !");
        }
        synchronized (TASK_QUEUE){
            if(TASK_QUEUE.size()>taskQueueSize){
                discardPolicy.disCard();
            }
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notify();
        }
    }

    /**
     * 对外提供：关闭线程池
     * 线程池中处于 BLOCKED 状态的线程未执行完，调用 interrupt（）方法打断
     * @throws InterruptedException
     */
    public void shutdown() throws InterruptedException {
        if(!TASK_QUEUE.isEmpty()){
            Thread.sleep(50);
        }
        synchronized (THREAD_QUEUE) {
            int initVal = THREAD_QUEUE.size(); //获取处于 BLOCKED 状态的
            while (initVal > 0) {
                for (WorkTask task : THREAD_QUEUE) {
                    if (task.getTaskState() == TaskState.BLOCKED) {
                        task.interrupt();
                        task.close(); // BLOCKED -> DEAD
                        initVal--;
                    } else {
                        Thread.sleep(10);
                    }
                }
            }
        }
        this.destroy=true;
        System.out.println("ThreadPool disposed !");
    }

    @Override
    public void run() {
        while (!destroy){
            System.out.printf("#Pool# min:%d, active:%d, max:%d, current:%d \n",
                    this.minSize,this.activeSize,this.maxSize,THREAD_QUEUE.size());
            try {
                Thread.sleep(2000);
                if(TASK_QUEUE.size()>activeSize && recordSize <activeSize){
                    for (int i = recordSize;i<activeSize;i++){
                        createWorkTask();
                    }
                    recordSize=this.activeSize;
                    System.out.printf("poolSize increase to activeSize : %d  -> ",this.activeSize);
                }else if(TASK_QUEUE.size()>maxSize && recordSize <maxSize){
                    for (int i = recordSize;i<maxSize;i++){
                        createWorkTask();
                    }
                    recordSize=this.maxSize;
                    System.out.printf("poolSize increase to maxSize : %d",this.maxSize);
                }

                synchronized (THREAD_QUEUE) {
                    if (TASK_QUEUE.isEmpty() && recordSize > activeSize) {
                        int releaseSize = recordSize - activeSize;
                        for (Iterator<WorkTask> it = THREAD_QUEUE.iterator(); it.hasNext(); ) {
                            if (releaseSize <= 0) {
                                break;
                            }
                            WorkTask task = it.next();
                            task.close();
                            task.interrupt();
                            it.remove();
                            releaseSize--;
                        }
                        recordSize = activeSize;
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class DiacardException extends RuntimeException{
        public DiacardException(String message) {
            super(message);
        }
    }

    public interface DiscardPolicy{
        void disCard() throws DiacardException, DataFormatException;
    }

    /**
     * 执行任务的 Thread 所处状态
     */
    private enum TaskState{
        FREE , RUNNING , BLOCKED , DEAD
    }




    /**
     * 单个线程（ Thread ） ，集中处理任务 Runnable
     */
    private static class WorkTask extends Thread{

        private volatile TaskState taskState = TaskState.FREE ;
        public WorkTask(ThreadGroup group, String s) {
            super(group,s);
        }
        public TaskState getTaskState(){
            return this.taskState;
        }

        /* 关闭 线程池 */
        public void close(){
            this.taskState = TaskState.DEAD;
        }

        @Override
        public void run(){ // 从 TASK_QUEUE 中取出任务，然后调用 每个 Runnable 的run方法执行
            OUTER:
            while (this.taskState != TaskState.DEAD){
                Runnable runnable;
                synchronized (TASK_QUEUE){//TASK_QUEUE中任务取完后，每次WorkTask取任务时都会进入BLOCKED状态，最终所有都进入BLOCKED，并且无法被唤醒。
                    while (TASK_QUEUE.isEmpty()){
                        try {//顺序：先记录状态，再调用wait（）方法使当前线程进入 BLOCKED，否则状态无法被记录
                            taskState=TaskState.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            break OUTER;
                        }
                    }
                    runnable = TASK_QUEUE.removeFirst();
                }
                if(runnable!=null){
                    taskState=TaskState.RUNNING;
                    runnable.run();
                    taskState=TaskState.FREE;
                }
            }
        }

    }

    /**
     * ------测试------
     * @param args
     * @throws DataFormatException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws DataFormatException, InterruptedException {

        //SimpleThreadPool pool =new SimpleThreadPool(6,10,SimpleThreadPool.DEFAULT_DISCARD_POLICY);
        SimpleThreadPool pool =new SimpleThreadPool();

        for(int i =0;i<60;i++){
            pool.submit(()->{
                System.out.println(Thread.currentThread().getName() + "  > working and start --- ");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "  > end ...");
                    }
            );
            System.out.println("------- submit one Runnable -------- ");
        }
        Thread.sleep(10000); //待线程执行完毕后，再 进行 shutdown 操作，避免 THREAD_QUEUE 锁冲突
        pool.shutdown();
    }
}
