package com.danny.hishop.business.order.domain;


import com.danny.hishop.framework.model.model.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author Danny
 * @Title: OrderDetailDO
 * @Description:
 * @Created on 2019-01-04 09:49:10
 */
@Data
@Accessors(chain = true)
public class OrderDetailDO extends BaseDO {
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
