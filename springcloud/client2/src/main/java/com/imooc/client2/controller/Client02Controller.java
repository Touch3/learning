package com.imooc.client2.controller;

import com.imooc.client2.FeignClients;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@DefaultProperties(defaultFallback = "defaultFallback") //没有指定降级逻辑方法时的默认降级策略。
public class Client02Controller {

    @Autowired
    private FeignClients feignClients;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 第一种调用方式：RestTemplate 直接调用指定地址，只能调用某个服务的一个实例。
     * 与方式二、三、四不同的是，他们通过服务名调用可以获得多个服务实例（负载均衡）
     * 缺点：获得被调用服务的地址，而且地址是写死的,一旦配置改变如端口号等则错误。
     * @return
     */
    @RequestMapping("/callClient1")
    public String callClient1(){
        RestTemplate template=new RestTemplate();
        String response=template.getForObject("http://localhost:9801/01Msg",String.class);
        return response;
    }

    /**
     * 第二种调用方式：LoadBalancerClient + RestTemplate，从服务实例名获取相应的 host 和 port
     * @return
     */
    @RequestMapping("/callClient2")
    public String callClient2(){
        ServiceInstance instance=loadBalancerClient.choose("CLIENT");
        String url=String.format("http://%s:%s/01Msg",instance.getHost(),instance.getPort());
        RestTemplate template=new RestTemplate();
        return template.getForObject(url,String.class);
    }

    /**
     * 第三种方式：重写 RestTemplate(加 @LoadBalanced + @Bean)
     * 将 RestTemplate 以注入的方式，直接使用服务实例名进行调服务
     * 也是经常使用的方式
     * 演示服务熔断
     * @return
     */
    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//熔断器是否开启的阀值，单位时间超过了阀值请求数，熔断器才开；
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//熔断器默认工作时间，超过此时间会进入半开状态，即允许流量做尝试；
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "30")//错误比例触发熔断；
            })
    @RequestMapping("/callClient3")
    public String callClient3(@RequestParam("number") Integer number){
        if(number%2==0){
            return "Seccess!";
        }else {
            return restTemplate.getForObject("http://CLIENT/01Msg", String.class);
        }
    }

    /**
     * 采用 Feign 调用方式（注解 + 接口）
     * 演示服务超时控制时间设置
     * @return
     */
//    @HystrixCommand(fallbackMethod="fallback",
//            commandProperties = {
//                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "4000" )
//            })
    @HystrixCommand
    @RequestMapping("/callClient4")
    public String callClient4(){
        return feignClients.feignClient();
    }

    public String fallback(){
        return "当前人数较多，请稍好重试 Q ！";
    }

    public String defaultFallback(){
        return "默认提示信息：请稍等！";
    }

}
