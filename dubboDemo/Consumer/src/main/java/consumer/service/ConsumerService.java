package consumer.service;

import base.dto.ProviderDTO;
import base.dto.ResultVO;
import base.service.IProviderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @Author: siteFounder
 */
@Slf4j
@Component
public class ConsumerService {

    @Reference(protocol = "dubbo",version = "11.0",group = "1")
    private IProviderService providerService1;

    @Reference(protocol = "dubbo",version = "11.0",group = "2")
    private IProviderService providerService2;

    public ResultVO getList1(){
        // 远程调用
        log.info("============开始调用服务===========");
        List<ProviderDTO> providerTestDTOList = providerService1.queryList();
        log.info("============服务调用完成===========");
        return new ResultVO.Builder<>().code(200).message("success").data(providerTestDTOList).build();
    }

    public ResultVO getList2(){
        // 远程调用
        log.info("============开始调用服务===========");
        List<ProviderDTO> providerTestDTOList = providerService2.queryList();
        log.info("============服务调用完成===========");
        return new ResultVO.Builder<>().code(200).message("success").data(providerTestDTOList).build();
    }

}
