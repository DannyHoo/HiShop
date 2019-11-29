package com.danny.hishop.business.aggregation.model.order.param;


import com.danny.hishop.business.aggregation.model.order.dto.OrderDetailDTO;
import com.danny.hishop.business.aggregation.model.user.dto.UserDTO;
import com.danny.hishop.framework.model.param.BaseParameter;

import java.util.List;

/**
 * @author Danny
 * @Title: CreateOrderParameter
 * @Description:
 * @Created on 2019-01-09 22:56:46
 */
public class CreateOrderParameter extends BaseParameter {

    private UserDTO userDTO;

    private List<OrderDetailDTO> orderDetailDTOList;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public CreateOrderParameter setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
        return this;
    }

    public List<OrderDetailDTO> getOrderDetailDTOList() {
        return orderDetailDTOList;
    }

    public CreateOrderParameter setOrderDetailDTOList(List<OrderDetailDTO> orderDetailDTOList) {
        this.orderDetailDTOList = orderDetailDTOList;
        return this;
    }
}
