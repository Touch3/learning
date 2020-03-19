package config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    /**
     * 从配置中心读取配置的值
     * 在bootstrap.yml 中设置值时，当服务启动优先读取，application.yml 反之
     */
    @Value("${testValue}")
    private String value;

    @Value("${server.port}")
    private String serverPort;

    @Value("${extConfig}")
    private String extValue;

    /**
     * http://localhost:8000/config/get
     * 测试从配置中心（dataId: nacos-config.properties）拉取配置信息
     * @return
     */
    @RequestMapping("/get")
    public String get() {
        return "testValue: "+value +"\n serverPort: "+ serverPort;
    }

    /**
     * http://localhost:7900/config/getExt
     * 从拓展文件读取配置信息
     * @return
     */
    @RequestMapping("/getExt")
    public  String getExt(){
        return "extConfig:" + extValue;
    }
}