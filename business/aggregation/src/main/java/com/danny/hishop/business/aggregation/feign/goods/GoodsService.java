package com.danny.hishop.business.aggregation.feign.goods;

import com.danny.hishop.business.aggregation.feign.user.UserFallback;
import com.danny.hishop.business.aggregation.feign.user.UserFallbackFactory;
import com.danny.hishop.business.aggregation.model.goods.dto.GoodsDTO;
import com.danny.hishop.business.aggregation.model.goods.param.GoodsParameter;
import com.danny.hishop.framework.model.response.Response;
import com.danny.hishop.framework.model.result.ServiceResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author huyuyang
 * @date 2019/11/29下午6:14
 */
@FeignClient(value = "hishop-goods", fallbackFactory = GoodsFallbackFactory.class)
public interface GoodsService {

    @GetMapping("/goods/goods/getByGoodsNo/{goodsNo}")
    Response<GoodsDTO> getByGoodsNo(@PathVariable String goodsNo);

    @PostMapping("/goods/goods/updateGoods")
    Response<Boolean> updateGoods(GoodsParameter goodsParameter);

}
