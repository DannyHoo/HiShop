package com.danny.hishop.business.aggregation.service;

import com.danny.hishop.business.aggregation.model.order.param.CreateOrderParameter;
import com.danny.hishop.business.aggregation.model.result.order.CreateOrderResult;
import com.danny.hishop.framework.model.result.ServiceResult;

/**
 * @author huyuyang
 * @date 2019/11/29下午10:14
 */
public interface OrderBusinessService {

    ServiceResult<String> createOrder(CreateOrderParameter createOrderParameter);

}
