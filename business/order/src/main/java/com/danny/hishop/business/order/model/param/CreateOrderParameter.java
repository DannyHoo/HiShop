package com.danny.hishop.business.order.model.param;


import com.danny.hishop.framework.model.param.BaseParameter;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Danny
 * @Title: CreateOrderParameter
 * @Description:
 * @Created on 2019-01-09 22:56:46
 */
@Data
@Accessors(chain = true)
public class CreateOrderParameter extends BaseParameter {

    private String userName;

    private List<OrderDetail> orderDetailList;

    public static class OrderDetail{
        private String goodsNo;
        private Integer num;
    }

}
