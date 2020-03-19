package com.imooc.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Client01Controller {

    @RequestMapping("/01Msg")
    public String client01Msg() throws InterruptedException {
        /** 设置线程休眠1秒 */
//        Thread.sleep(1000);
        return "client+03+Msg";
    }

}
