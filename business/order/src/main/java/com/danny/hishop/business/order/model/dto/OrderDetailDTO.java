package com.danny.hishop.business.order.model.dto;


import com.danny.hishop.framework.model.dto.BaseDTO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author Danny
 * @Title: OrderDetailDTO
 * @Description:
 * @Created on 2019-02-25 17:39:08
 */
@Data
@Accessors(chain = true)
public class OrderDetailDTO extends BaseDTO {

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
