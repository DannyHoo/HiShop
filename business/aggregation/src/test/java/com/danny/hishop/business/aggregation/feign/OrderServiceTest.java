package com.danny.hishop.business.aggregation.feign;

import com.alibaba.fastjson.JSONObject;
import com.danny.hishop.business.aggregation.AggregationApplicationTests;
import com.danny.hishop.business.aggregation.feign.order.OrderService;
import com.danny.hishop.business.aggregation.feign.user.UserService;
import com.danny.hishop.business.aggregation.model.order.dto.OrderDTO;
import com.danny.hishop.business.aggregation.model.order.dto.OrderDetailDTO;
import com.danny.hishop.business.aggregation.model.order.param.OrderDetailListParameter;
import com.danny.hishop.business.aggregation.model.order.param.OrderParameter;
import com.danny.hishop.business.aggregation.model.user.dto.AddressDTO;
import com.danny.hishop.framework.model.response.Response;
import com.danny.hishop.framework.model.result.ServiceResult;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/11/29下午10:32
 */
public class OrderServiceTest extends AggregationApplicationTests {
    @Autowired
    private OrderService orderService;

    @Test
    public void saveOrderTest() {
        Response<OrderDTO> result = orderService.saveOrder(getOrderParameter());
        System.out.println(JSONObject.toJSONString(result));
    }

    @Test
    public void saveOrderDetailList() {
        String orderNo = "2309j029e2ofn2eofe";
        Response<List<OrderDetailDTO>> result = orderService.saveOrderDetailList(getOrderDetailListParameter(orderNo));
        System.out.println(JSONObject.toJSONString(result));
    }

    private OrderDetailListParameter getOrderDetailListParameter(String orderNo) {
        List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();
        orderDetailDTOList.add(new OrderDetailDTO().setTotalPrice(BigDecimal.ZERO)
                .setActualPrice(BigDecimal.ZERO)
                .setOrderNo(orderNo));
        return new OrderDetailListParameter().setOrderDetailDTOList(orderDetailDTOList);
    }


    /**
     * 组装订单信息
     *
     * @return
     */
    private OrderParameter getOrderParameter() {
        return new OrderParameter()
                .setOrderNo(UUID.randomUUID().toString().replace("-", ""))
                .setUserName("username")
                .setReceiverName("张三")
                .setReceiverMobileNo("13049500697")
                .setReceiverAddress("北京市海淀区")
                .setPayType(10)
                .setStatus(10)
                .setDeliverType(10)
                .setDeliverTime(10)
                .setTotalPrice(BigDecimal.valueOf(109.3))
                .setCutDownPrice(BigDecimal.ZERO)
                .setFreight(BigDecimal.TEN)
                .setActualPrice(BigDecimal.ZERO);
    }
}
