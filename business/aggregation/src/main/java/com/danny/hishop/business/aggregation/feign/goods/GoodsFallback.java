package com.danny.hishop.business.aggregation.feign.goods;

import com.danny.hishop.business.aggregation.model.goods.dto.GoodsDTO;
import com.danny.hishop.business.aggregation.model.goods.param.GoodsParameter;
import com.danny.hishop.framework.model.response.Response;
import com.danny.hishop.framework.model.result.ServiceResult;
import org.springframework.stereotype.Component;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/11/29下午6:16
 */
@Component
public class GoodsFallback implements GoodsService {

    @Override
    public Response<GoodsDTO> getByGoodsNo(String goodsNo) {
        return null;
    }

    @Override
    public Response<Boolean> updateGoods(GoodsParameter goodsParameter) {
        return null;
    }
}
