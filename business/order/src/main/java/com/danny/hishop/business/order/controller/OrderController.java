package com.danny.hishop.business.order.controller;


import com.danny.hishop.business.order.model.param.CreateOrderParameter;
import com.danny.hishop.business.order.model.param.OrderParameter;
import com.danny.hishop.business.order.service.OrderService;
import com.danny.hishop.framework.model.response.Response;
import com.danny.hishop.framework.model.result.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author huyuyang
 * @date 2019/11/26下午9:36
 */
@RestController
@RequestMapping("/order/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/get/{userName}", method = RequestMethod.GET)
    public Response get(@PathVariable String userName) {
        ServiceResult result = null;
        return Response.build(result);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Response save(@RequestBody OrderParameter orderParameter) throws Exception {
        ServiceResult result = orderService.saveOrder(orderParameter);
        return Response.build(result);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Response create(@RequestBody CreateOrderParameter createOrderParameter) throws Exception {
        ServiceResult result = orderService.createOrder(createOrderParameter);
        return Response.build(result);
    }


}
