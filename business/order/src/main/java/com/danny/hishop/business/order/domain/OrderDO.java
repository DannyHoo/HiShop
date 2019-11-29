package com.danny.hishop.business.order.domain;


import com.danny.hishop.framework.model.model.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author Danny
 * @Title: OrderDO
 * @Description:
 * @Created on 2019-01-04 09:49:02
 */
@Data
@Accessors(chain = true)
public class OrderDO extends BaseDO {
    /* 订单号 */
    private String orderNo;
    /* 用户名 */
    private String userName;
    /* 收货人 */
    private String receiverName;
    /* 收货人电话 */
    private String receiverMobileNo;
    /* 收货地址 */
    private String receiverAddress;
    /* 支付方式：10在线付款20货到付款30他人代付 */
    private Integer payType;
    /* 订单状态：10待付款，20已付款待发货，30已退货，40已完成 */
    private Integer status;
    /* 配送方式：10快递配送20上门自取 */
    private Integer deliverType;
    /* 送货时间：10不限送货时间20工作日送货30节假日配送 */
    private Integer deliverTime;
    /* 订单总金额 */
    private BigDecimal totalPrice;
    /* 订单优惠金额 */
    private BigDecimal cutDownPrice;
    /* 运费 */
    private BigDecimal freight;
    /* 应付总额 */
    private BigDecimal actualPrice;

}
