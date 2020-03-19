package com.imooc.client2;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 声明调用的服务名称：client
 */
@FeignClient(name = "client")
public interface FeignClients {

    /**
     * 声明调用服务client 的接口方法
     * @return
     */
    @RequestMapping("/01Msg")
    String feignClient();
}
