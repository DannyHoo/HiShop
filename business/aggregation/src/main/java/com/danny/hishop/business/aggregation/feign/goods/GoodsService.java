package com.danny.hishop.business.aggregation.feign.goods;

import com.danny.hishop.business.aggregation.feign.user.UserFallback;
import com.danny.hishop.business.aggregation.feign.user.UserFallbackFactory;
import com.danny.hishop.business.aggregation.model.goods.dto.GoodsDTO;
import com.danny.hishop.business.aggregation.model.goods.param.GoodsParameter;
import com.danny.hishop.framework.model.result.ServiceResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/11/29下午6:14
 */
@FeignClient(value = "HISHOP-GOODS", fallback = GoodsFallback.class,fallbackFactory = GoodsFallbackFactory.class)
public interface GoodsService {

    @GetMapping("/goods/goods/getByGoodsNo/{goodsNo}")
    ServiceResult<GoodsDTO> getByGoodsNo(@PathVariable String goodsNo);

    @PostMapping("/goods/goods/updateGoods")
    ServiceResult<Boolean> updateGoods(GoodsParameter goodsParameter);

}
