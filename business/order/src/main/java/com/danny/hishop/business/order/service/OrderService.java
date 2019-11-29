package com.danny.hishop.business.order.service;

import com.danny.hishop.business.order.model.dto.OrderDTO;
import com.danny.hishop.business.order.model.param.OrderParameter;
import com.danny.hishop.framework.model.result.ServiceResult;

/**
 * @author Danny
 * @Title: OrderService
 * @Description:
 * @Created on 2019-01-09 23:10:06
 */
public interface OrderService {
    public ServiceResult<OrderDTO> saveOrder(OrderParameter orderParameter);
}
