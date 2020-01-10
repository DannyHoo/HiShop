package com.danny.hishop.mockclient.feign;

import com.alibaba.fastjson.JSONObject;
import com.danny.hishop.mockclient.fallback.GatewayFallback;
import com.danny.hishop.mockclient.fallback.GatewayFallbackFactory;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2020/1/6下午5:32
 */
@FeignClient(name = "gatewayFeignClient", url = "${config.hishop.gateway.host}", fallbackFactory = GatewayFallbackFactory.class)
public interface GatewayFeignClient {

    /**
     * {"orderDetailDTOList":[{"goodsNo":"G20180614161040114149","goodsNum":1}],"userDTO":{"userName":"82Z76oIu"}}
     *
     * @param json
     * @return
     */
    @PostMapping("/api/aggregation/order/create")
    JSONObject createOrder(@RequestBody JSONObject json);

}
