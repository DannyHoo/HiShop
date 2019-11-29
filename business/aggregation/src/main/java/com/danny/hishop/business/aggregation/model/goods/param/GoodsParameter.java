package com.danny.hishop.business.aggregation.model.goods.param;


import com.danny.hishop.framework.model.param.BaseParameter;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author Danny
 * @Title: GoodsParameter
 * @Description:
 * @Created on 2019-02-26 11:26:20
 */
@Data
@Accessors(chain = true)
public class GoodsParameter extends BaseParameter {
    /* 商品编号 */
    private String goodsNo;
    /* 商品名称 */
    private String goodsName;
    /* 原价 */
    private BigDecimal originPrice;
    /* 现价 */
    private BigDecimal nowPrice;
    /* 商品总数量 */
    private int totalNum;
    /* 剩余数量 */
    private int balance;
    /*  */
    private String description;
    /*  */
    private String pictureUrls;
    /* 商品状态 10正常 20下架 */
    private Integer status;

}
