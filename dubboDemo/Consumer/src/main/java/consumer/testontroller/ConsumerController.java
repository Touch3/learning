package consumer.testontroller;

import base.dto.ResultVO;
import consumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: siteFounder
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @RequestMapping(value = "/list1",method = RequestMethod.GET)
    public ResultVO controller1(){
        return consumerService.getList1();
    }

    @RequestMapping(value = "/list2",method = RequestMethod.GET)
    public ResultVO controller2(){
        return consumerService.getList2();
    }

}