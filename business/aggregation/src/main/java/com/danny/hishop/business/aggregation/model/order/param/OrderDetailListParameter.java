package com.danny.hishop.business.aggregation.model.order.param;


import com.danny.hishop.business.order.model.dto.OrderDetailDTO;
import com.danny.hishop.framework.model.param.BaseParameter;

import java.util.List;

/**
 * @author Danny
 * @Title: OrderDetailListParameter
 * @Description:
 * @Created on 2019-02-26 16:38:05
 */
public class OrderDetailListParameter extends BaseParameter {

    private List<OrderDetailDTO> orderDetailDTOList;

    public List<OrderDetailDTO> getOrderDetailDTOList() {
        return orderDetailDTOList;
    }

    public OrderDetailListParameter setOrderDetailDTOList(List<OrderDetailDTO> orderDetailDTOList) {
        this.orderDetailDTOList = orderDetailDTOList;
        return this;
    }
}
