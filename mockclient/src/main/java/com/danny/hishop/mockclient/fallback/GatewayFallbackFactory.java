package com.danny.hishop.mockclient.fallback;

import com.alibaba.fastjson.JSONObject;
import com.danny.hishop.framework.model.enums.ResultStatusEnum;
import com.danny.hishop.mockclient.feign.GatewayFeignClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2020/1/6下午6:47
 */
@Slf4j
@Component
public class GatewayFallbackFactory implements FallbackFactory<GatewayFeignClient> {
    @Override
    public GatewayFeignClient create(Throwable throwable) {
        return new GatewayFeignClient(){

            @Override
            public JSONObject createOrder(JSONObject json) {
                log.info("GatewayFallback.createOrder {} params:{}", ResultStatusEnum.SERVICE_FUSE_OPEN.getCode(),json);
                return new JSONObject();
            }

        };
    }
}
