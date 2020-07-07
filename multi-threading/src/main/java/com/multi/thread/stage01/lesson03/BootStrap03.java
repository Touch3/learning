package com.multi.thread.stage01.lesson03;

/**
 * @Description: 线程优雅 stop
 * @Author: siteFounder
 */
public class BootStrap03 {
    public static void main(String[] args) {

        ThreadService service=new ThreadService();
        long start = System.currentTimeMillis();
        service.execute(() ->{
//            while (true){}
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        service.shutdown(5_000);
        long end = System.currentTimeMillis();

        System.out.println(end-start);

    }
}
