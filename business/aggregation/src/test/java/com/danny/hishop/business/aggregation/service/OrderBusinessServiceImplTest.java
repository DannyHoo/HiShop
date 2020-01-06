package com.danny.hishop.business.aggregation.service;

import com.alibaba.fastjson.JSON;
import com.danny.hishop.business.aggregation.AggregationApplicationTests;
import com.danny.hishop.business.aggregation.model.order.dto.OrderDetailDTO;
import com.danny.hishop.business.aggregation.model.order.param.CreateOrderParameter;
import com.danny.hishop.business.aggregation.model.result.order.CreateOrderResult;
import com.danny.hishop.business.aggregation.model.user.dto.UserDTO;
import com.danny.hishop.framework.model.result.ServiceResult;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/12/18下午5:01
 */
public class OrderBusinessServiceImplTest extends AggregationApplicationTests {

    @Autowired
    private OrderBusinessService orderBusinessService;

    @Test
    public void createOrderTest() {
        for (int i=0;i<1;i++){
            List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();
            orderDetailDTOList.add(new OrderDetailDTO().setGoodsNo("G20180614161040114149").setGoodsNum(1));
            CreateOrderParameter createOrderParameter = new CreateOrderParameter()
                    .setUserDTO(new UserDTO().setUserName("82Z76oIu"))
                    .setOrderDetailDTOList(orderDetailDTOList);
            System.out.println(JSON.toJSONString(createOrderParameter));
            ServiceResult<String> serviceResult = orderBusinessService.createOrder(createOrderParameter);
            printResult(serviceResult);
        }
    }

}
