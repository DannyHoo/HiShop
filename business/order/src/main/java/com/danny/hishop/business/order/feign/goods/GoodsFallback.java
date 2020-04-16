package com.danny.hishop.business.order.feign.goods;

import com.danny.hishop.business.order.feign.goods.dto.GoodsDTO;
import com.danny.hishop.business.order.feign.goods.param.GoodsParameter;
import com.danny.hishop.framework.model.response.Response;
import org.springframework.stereotype.Component;

/**
 * @author huyuyang
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
