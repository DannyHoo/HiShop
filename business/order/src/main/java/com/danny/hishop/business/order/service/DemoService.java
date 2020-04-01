package com.danny.hishop.business.order.service;

import com.danny.hishop.business.order.domain.OrderDO;
import com.danny.hishop.business.order.service.impl.DemoServiceImpl;

/**
 *
 * @date 2020/3/16下午10:57
 */
public interface DemoService {

    public Boolean asyncFunctionTest();

    DemoServiceImpl.CreateOrderResult createOrder();
}
