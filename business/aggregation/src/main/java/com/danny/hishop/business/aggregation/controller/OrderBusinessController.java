package com.danny.hishop.business.aggregation.controller;

import com.danny.hishop.business.aggregation.model.order.param.CreateOrderParameter;
import com.danny.hishop.business.aggregation.service.OrderBusinessService;
import com.danny.hishop.framework.model.response.Response;
import com.danny.hishop.framework.model.result.ServiceResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/12/28上午11:12
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderBusinessController {

    @Autowired
    private OrderBusinessService orderBusinessService;

    /**
     * 同步下单
     *
     * @param httpServletRequest
     * @param createOrderParameter
     * @return
     */
    @RequestMapping("/create")
    public Response createOrderSync(HttpServletRequest httpServletRequest, @RequestBody CreateOrderParameter createOrderParameter) {
        ServiceResult result = orderBusinessService.createOrder(createOrderParameter);
        return Response.build(result);
    }

}
