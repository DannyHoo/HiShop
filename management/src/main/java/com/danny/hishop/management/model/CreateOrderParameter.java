package com.danny.hishop.management.model;


import com.danny.hishop.framework.model.dto.BaseDTO;
import com.danny.hishop.framework.model.param.BaseParameter;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
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

    @Data
    @Accessors(chain = true)
    public static class UserDTO extends BaseDTO {
        private String userName;
        private String mobileNo;
        private String salt;
        private String password;
        private String email;
        private String realName;
        private String idCardNo;
    }

    @Data
    @Accessors(chain = true)
    public static class OrderDetailDTO extends BaseDTO {
        /* 订单号 */
        private String orderNo;
        /* 商品编号 */
        private String goodsNo;
        /* 商品数量 */
        private int goodsNum;
        /* 订单总金额 */
        private BigDecimal totalPrice;
        /* 应付价格 */
        private BigDecimal actualPrice;

    }
}
