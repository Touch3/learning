package com.multi.thread.stage01.lesson03;

/**
 * @Description: 线程 优雅起停
 * @Author: siteFounder
 */
public class ThreadService {

    /** 执行线程（主线程），不做具体的事情*/
    private Thread executeThread;

    /** task 是否执行结束判断 */
    private boolean finished =false;

    /** 守护线程 为真正的任务线程
     * 传入一个待执行的任务，同时将该任务设置成为守护线程;
     * 调用守护线程的join()方法使得先执行守护线程 task,
     * 当主线程结束后，当前的守护线程（task）会自然结束.
     * @param task
     */
    public void execute(Runnable task){
        executeThread =new Thread(){
            @Override
            public void run() {
                //另开启 任务线程 独立执行
                Thread runner =new Thread(task);
                runner.setDaemon(true);
                runner.start();
                try {
                    runner.join(); //executeThread 主线程处于 blocked 状态，优先执行守护线程（task）
                    finished=true; //task 执行结束时，标记结束
                } catch (InterruptedException e) { }

            }
        };
        executeThread.start();
    }

    /**
     * 当task 执行时间超过 mills，时，则会结束任务。
     * @param mills ：执行的任务执行时间。
     */
    public void shutdown(long mills){
        long currentTime = System.currentTimeMillis();
        while (!finished){
            if((System.currentTimeMillis()-currentTime)>mills){
                executeThread.interrupt(); //执行线程 executeThread 结束后，task也会结束
                break;
            }
            try {
                executeThread.sleep(1);
            } catch (InterruptedException e) {
                break;
            }
        }
        finished=false;
    }

}
