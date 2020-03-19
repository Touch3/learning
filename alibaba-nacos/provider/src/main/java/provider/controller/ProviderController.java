package provider.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务提供者：提供下面两个服务
 */
@RefreshScope
@RestController
@RequestMapping("/provider")
public class ProviderController {

    /**
     * http://localhost:8100/provider/value
     * @return
     */
    @GetMapping("/value")
    public String testP1() {
        return " Nacos Provider Service001 !" ;
    }


    /**
     * http://localhost:8100/provider/seccess
     * @param str
     * @return
     */
    @GetMapping("{str}")
    public String testP2(@PathVariable String str) {
        return "Nacos Provider Service002 ! " + str;
    }

}


