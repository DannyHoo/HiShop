package com.danny.hishop.mockclient.feign;

import com.alibaba.fastjson.JSONObject;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/11/23下午10:15
 */
@FeignClient(name = "commonFeignClient", url = "${config.hishop.common.host}")
public interface CommonFeignClient {

    @PostMapping("/open/encrypt/getRSA")
    JSONObject getRSA();

    @PostMapping("/open/encrypt/getKey")
    @Headers({"ContentType:application/json"})
    JSONObject getKey(Object jsonObject);

    @PostMapping("/api/users/lookupCurrentUser")
    @Headers({"ContentType:application/json"})
    JSONObject getOpayUserInfo(@RequestHeader("Authorization") String token);

    @PostMapping("/api/users/queryBvnInfo")
    @Headers({"ContentType:application/json"})
    JSONObject queryBvnInfo(String json);
}
