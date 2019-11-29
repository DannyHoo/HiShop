package com.danny.hishop.business.goods.model.dto;


import com.danny.hishop.framework.model.dto.BaseDTO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author Danny
 * @Title: GoodsDTO
 * @Description:
 * @Created on 2019-02-25 17:30:37
 */
@Data
@Accessors(chain = true)
public class GoodsDTO extends BaseDTO {
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
