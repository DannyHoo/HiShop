package com.danny.hishop.mockclient.feign.callback;

import com.alibaba.fastjson.JSONObject;
import com.danny.hishop.mockclient.feign.CommonFeignClient;
import org.springframework.stereotype.Component;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/11/23下午10:19
 */
@Component
public class CommonFeignFactory implements CommonFeignClient {

    @Override
    public JSONObject getRSA() {
        return null;
    }

    @Override
    public JSONObject getKey(Object jsonObject) {
        return null;
    }

    @Override
    public JSONObject getOpayUserInfo(String token) {
        return null;
    }

    @Override
    public JSONObject queryBvnInfo(String json) {
        return null;
    }
}
