package com.danny.hishop.business.order.service;


import com.danny.hishop.business.order.domain.OrderDetailDO;
import com.danny.hishop.business.order.model.dto.OrderDetailDTO;
import com.danny.hishop.business.order.model.param.OrderDetailListParameter;
import com.danny.hishop.framework.model.result.ServiceResult;

import java.util.List;

/**
 * @author Danny
 * @Title: OrderDetailService
 * @Description:
 * @Created on 2019-02-26 16:37:42
 */
public interface OrderDetailService {

    ServiceResult<List<OrderDetailDTO>> saveOrderDetailList(OrderDetailListParameter orderDetailListParameter);

    OrderDetailDO saveOrderDetail(OrderDetailDO orderDetailDO);

}
