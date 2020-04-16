package com.danny.hishop.business.goods.model.param;

import com.danny.hishop.framework.model.param.BaseParameter;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2020/4/16下午2:01
 */
@Data
@Accessors(chain = true)
public class ReduceStockParameter extends BaseParameter {
    private Long goodsId;
    private String goodsNo;
    private Integer num;
}
