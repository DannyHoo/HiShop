package com.danny.hishop.business.order.feign.goods.result;

import com.danny.hishop.business.order.model.dto.OrderDTO;
import com.danny.hishop.business.order.model.dto.OrderDetailDTO;
import com.danny.hishop.framework.model.BaseModel;

import java.util.List;

/**
 * @author Danny
 * @Title: CreateOrderResult
 * @Description:
 * @Created on 2019-01-09 22:54:28
 */
public class CreateOrderResult extends BaseModel {

    private OrderDTO orderDTO;
    private List<OrderDetailDTO> orderDetailDTOList;

    public OrderDTO getOrderDTO() {
        return orderDTO;
    }

    public CreateOrderResult setOrderDTO(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
        return this;
    }

    public List<OrderDetailDTO> getOrderDetailDTOList() {
        return orderDetailDTOList;
    }

    public CreateOrderResult setOrderDetailDTOList(List<OrderDetailDTO> orderDetailDTOList) {
        this.orderDetailDTOList = orderDetailDTOList;
        return this;
    }
}
