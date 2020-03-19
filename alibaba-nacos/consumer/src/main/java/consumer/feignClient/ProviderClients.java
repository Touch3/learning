package consumer.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "nacos-provider")
public interface ProviderClients {

    @GetMapping("/provider/value")
    public String testP1();
}
