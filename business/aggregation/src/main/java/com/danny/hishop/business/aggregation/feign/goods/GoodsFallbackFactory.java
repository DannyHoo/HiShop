package com.danny.hishop.business.aggregation.feign.goods;

import com.danny.hishop.business.aggregation.model.goods.dto.GoodsDTO;
import com.danny.hishop.business.aggregation.model.goods.param.GoodsParameter;
import com.danny.hishop.framework.model.enums.ResultStatusEnum;
import com.danny.hishop.framework.model.response.Response;
import com.danny.hishop.framework.model.result.ServiceResult;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author huyuyang
 * @date 2019/11/29下午6:16
 */
@Component
public class GoodsFallbackFactory  implements FallbackFactory<GoodsService> {

    @Override
    public GoodsService create(Throwable throwable) {
        return new GoodsService() {
            @Override
            public Response<GoodsDTO> getByGoodsNo(String goodsNo) {
                throwable.printStackTrace();
                return Response.build(ResultStatusEnum.SERVICE_FUSE_OPEN);
            }

            @Override
            public Response<Boolean> updateGoods(GoodsParameter goodsParameter) {
                throwable.printStackTrace();
                return Response.build(ResultStatusEnum.SERVICE_FUSE_OPEN);
            }
        };
    }
}