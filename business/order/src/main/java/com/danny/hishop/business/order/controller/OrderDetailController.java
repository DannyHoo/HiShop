package com.danny.hishop.business.order.controller;

import com.danny.hishop.business.order.model.param.OrderDetailListParameter;
import com.danny.hishop.business.order.model.param.OrderParameter;
import com.danny.hishop.business.order.service.OrderDetailService;
import com.danny.hishop.framework.model.response.Response;
import com.danny.hishop.framework.model.result.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/11/26下午9:36
 */
@RestController
@RequestMapping("/order/detail/")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Response save(@RequestBody OrderDetailListParameter orderDetailListParameter) throws Exception {
        ServiceResult result = orderDetailService.saveOrderDetailList(orderDetailListParameter);
        return Response.build(result);
    }

}
