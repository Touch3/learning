package consumer.controller;

import consumer.feignClient.ProviderClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 服务消费者
 * 调用provider中提供的两个服务
 *
 */
@RestController
@RequestMapping("/consumer")
@Slf4j
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProviderClients providerClients;

    /**
     * RestTemplate 方式调用
     *http://localhost:8200/consumer/one
     * @return
     */
    @GetMapping("/one")
    public String getForOne() {
        log.info("---------consumer getFor provider Service1 -----------");
        return restTemplate.getForObject("http://nacos-provider/provider/value", String.class);
    }

    /**
     * RestTemplate 方式调用
     * http://localhost:8200/consumer/two/{value} ---> service001
     * http://localhost:8200/consumer/two/{s} ---> service001
     * @param str
     * @return
     */
    @GetMapping("two/{str}")
    public String getForTwo(@PathVariable String str) {
        log.info("---------consumer getFor provider Service2 -----------");
        return restTemplate.getForObject("http://nacos-provider/provider/"+str, String.class);
    }

    /**
     *  openfeign 方式调用
     * http://localhost:8200/provider/consumer/three
     * @return
     */
    @GetMapping("/three")
    public String feign(){
        return providerClients.testP1();
    }
}