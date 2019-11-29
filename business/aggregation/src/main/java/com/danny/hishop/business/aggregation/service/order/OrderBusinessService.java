package com.danny.hishop.business.aggregation.service.order;


import com.danny.hishop.business.aggregation.model.order.param.CreateOrderParameter;
import com.danny.hishop.business.aggregation.model.result.order.CreateOrderResult;
import com.danny.hishop.framework.model.result.ServiceResult;

/**
 * @author Danny
 * @Title: OrderBusinessService
 * @Description:
 * @Created on 2018-12-21 16:06:03
 */
public interface OrderBusinessService {

    public ServiceResult<CreateOrderResult> createOrder(CreateOrderParameter createOrderParameter);

}
