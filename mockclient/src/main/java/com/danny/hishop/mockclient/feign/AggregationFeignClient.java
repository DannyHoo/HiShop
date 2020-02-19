package com.danny.hishop.mockclient.feign;

import com.alibaba.fastjson.JSONObject;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author huyuyang
 * @date 2020/1/6下午5:32
 */
@FeignClient(name = "aggregationFeignClient", url = "${config.hishop.aggregation.host}")
public interface AggregationFeignClient {

    /**
     * {"orderDetailDTOList":[{"goodsNo":"G20180614161040114149","goodsNum":1}],"userDTO":{"userName":"82Z76oIu"}}
     *
     * @param json
     * @return
     */
    @PostMapping("/order/create")
    //@Headers({"ContentType:application/json"})
    JSONObject createOrder(@RequestBody JSONObject json);

}
