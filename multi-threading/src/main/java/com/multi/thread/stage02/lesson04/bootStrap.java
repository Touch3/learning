package com.multi.thread.stage02.lesson04;

/**
 * @Description:
 * @Author: siteFounder
 */
public class bootStrap {

    public static void main(String[] args) {

        Gate gate=new Gate();

        User sh =new User("sangLao","shangHai",gate);
        User bj =new User("beiLao","beijing",gate);
        User gz =new User("guangLao","guangzhou",gate);

        sh.start();
        bj.start();
        gz.start();

    }
}
