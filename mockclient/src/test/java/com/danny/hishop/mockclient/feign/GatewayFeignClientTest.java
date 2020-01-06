package com.danny.hishop.mockclient.feign;

import com.alibaba.fastjson.JSONObject;
import com.danny.hishop.mockclient.MockClientApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2020/1/6下午5:41
 */
public class GatewayFeignClientTest extends MockClientApplicationTests {

    @Autowired
    private GatewayFeignClient gatewayFeignClient;

    /**
     * {"orderDetailDTOList":[{"goodsNo":"G20180614161040114149","goodsNum":1}],"userDTO":{"userName":"82Z76oIu"}}
     */
    @Test
    public void createOrderTest() {
        JSONObject param = new JSONObject();
        JSONObject userDTO = new JSONObject();
        userDTO.put("userName", "82Z76oIu");
        List<JSONObject> orderDetailDTOList = new ArrayList();
        JSONObject orderDetailDTO = new JSONObject();
        orderDetailDTO.put("goodsNo", "G20180614161040114149");
        orderDetailDTO.put("goodsNum", 1);
        orderDetailDTOList.add(orderDetailDTO);

        param.put("userDTO", userDTO);
        param.put("orderDetailDTOList", orderDetailDTOList);

        JSONObject jsonObject = gatewayFeignClient.createOrder(param);
        printResult(jsonObject);
    }
}
