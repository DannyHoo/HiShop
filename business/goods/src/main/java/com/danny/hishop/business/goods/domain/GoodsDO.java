package com.danny.hishop.business.goods.domain;


import com.danny.hishop.framework.model.model.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author Danny
 * @Title: GoodsDO
 * @Description:
 * @Created on 2019-01-09 22:34:17
 */
@Data
@Accessors(chain = true)
public class GoodsDO extends BaseDO {
    private Long id;
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
    /* 版本 */
    private Integer version;

}
