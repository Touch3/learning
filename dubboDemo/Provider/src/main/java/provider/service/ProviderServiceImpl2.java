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
 * @CreateTime 2020/08/09
 * desc: @Service声明是一个服务，nacos上的服务信息
 */
@Slf4j
//@Service(version = "11.0",timeout = 500,retries = 5) //针对某一个服务设置超时与重试
@Service(version = "11.0",group = "2")
public class ProviderServiceImpl2 implements IProviderService {

    @Override
    public List<ProviderDTO> queryList() {
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            log.info(e.getCause().getMessage());
        }
        log.info("============server.port:{},interfaceImpl--2==============",System.getProperty("server.port"));
        List<ProviderDTO> list = new ArrayList<>();
        ProviderDTO person = new ProviderDTO();
        person.setId(1);
        person.setName("zhu");
        person.setNumber(100);
        list.add(person);
        return list;
    }
}