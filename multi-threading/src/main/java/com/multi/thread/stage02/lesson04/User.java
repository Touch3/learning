package com.multi.thread.stage02.lesson04;

/**
 * @Description:
 * @Author: siteFounder
 */
public class User extends Thread {

    private final String myName;

    private final String myAddress;

    private final Gate gate;

    public User(String myName, String myAddress, Gate gate) {
        this.myName = myName;
        this.myAddress = myAddress;
        this.gate = gate;
    }

    @Override
    public void run() {
        System.out.println(myName+"  Begin---");
        while (true){
            this.gate.pass(myName,myAddress);
        }
    }
}
