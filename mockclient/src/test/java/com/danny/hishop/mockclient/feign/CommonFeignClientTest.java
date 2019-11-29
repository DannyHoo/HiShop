package com.danny.hishop.mockclient.feign;

import com.alibaba.fastjson.JSONObject;
import com.danny.hishop.mockclient.MockClientApplicationTests;
import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/11/25下午2:04
 */
public class CommonFeignClientTest extends MockClientApplicationTests {

    @Autowired
    private CommonFeignClient commonFeignClient;

    @Test
    public void getRSATest() {
        System.out.println(getRSA().toJSONString());
    }

    private JSONObject getRSA() {
        JSONObject jsonObject = commonFeignClient.getRSA();
        return jsonObject;
    }

    @Test
    public void getKeyTest() throws Exception {
        JSONObject jsonObject = commonFeignClient.getKey(new KeyRequest());
    }


    @Data
    @Accessors(chain = true)
    static class KeyRequest {
        private String clientEncryptPublicKey;
    }
}
