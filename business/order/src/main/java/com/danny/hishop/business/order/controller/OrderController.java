package com.danny.hishop.business.order.controller;


import com.danny.hishop.business.order.model.param.OrderParameter;
import com.danny.hishop.business.order.service.OrderService;
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
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/get/{userName}", method = RequestMethod.GET)
    public Response get(@PathVariable String userName) {
        ServiceResult result = null;
        return Response.buildSuccess(result.getData());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Response save(@RequestBody OrderParameter orderParameter) throws Exception {
        ServiceResult result = null;

        return Response.buildSuccess(result);
    }

}
