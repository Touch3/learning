package provider.service;

import base.dto.ProviderDTO;
import base.service.IProviderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * RPC接口实现类
 * @Author siteFound
 * @CreateTime 2020/08/092
 * desc: @Service声明是一个服务，nacos上的服务信息
 */
@Slf4j
//@Service(version = "11.0",timeout = 500,retries = 5)
@Service(version = "11.0",group = "1")
public class ProviderServiceImpl1 implements IProviderService {

    @Override
    public List<ProviderDTO> queryList() {
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            log.info(e.getCause().getMessage());
        }
        log.info("============server.port:{},interfaceImpl--1==============",System.getProperty("server.port"));
        List<ProviderDTO> list = new ArrayList<>();
        ProviderDTO person = new ProviderDTO();
        person.setId(1);
        person.setName("zhu");
        person.setNumber(100);
        list.add(person);
        return list;
    }
}