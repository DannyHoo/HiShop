package com.danny.hishop.business.aggregation.feign.goods;

import com.danny.hishop.business.aggregation.model.goods.dto.GoodsDTO;
import com.danny.hishop.framework.model.result.ServiceResult;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/11/29下午6:16
 */
public class GoodsFallback implements GoodsService {
    @Override
    public ServiceResult<GoodsDTO> getByGoodsNo(String goodsNo) {
        return null;
    }
}