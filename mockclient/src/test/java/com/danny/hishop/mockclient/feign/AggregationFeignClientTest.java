package com.danny.hishop.mockclient.feign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.danny.hishop.framework.util.http.HttpClientUtils;
import com.danny.hishop.framework.util.test.Executor;
import com.danny.hishop.framework.util.test.ExecutorInterface;
import com.danny.hishop.mockclient.MockClientApplicationTests;
import com.google.gson.JsonObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2020/1/6下午5:41
 */
public class AggregationFeignClientTest extends MockClientApplicationTests {

    @Autowired
    private AggregationFeignClient aggregationFeignClient;
    private JSONObject getJSONObject() {
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
        return param;
    }

    /**
     * {"orderDetailDTOList":[{"goodsNo":"G20180614161040114149","goodsNum":1}],"userDTO":{"userName":"82Z76oIu"}}
     */
    @Test
    public void createOrderByMockClientTest() throws InterruptedException {
        final AtomicInteger atomicInteger=new AtomicInteger(0);
        Executor executor = new Executor(new ExecutorInterface() {
            @Override
            public void executeJob(){
                for (int i = 0; i < 1; i++) {
                    try {
                        JSONObject param = getJSONObject();
                        JSONObject jsonObject = aggregationFeignClient.createOrder(param);
                        printResult(jsonObject);
                        if (jsonObject.getInteger("code")==100000){
                            atomicInteger.addAndGet(1);
                        }else{
                            System.out.println("ERROR:");
                            System.out.println(JSON.toJSONString(jsonObject));
                        }
                    } catch (Exception e) {
                    }
                }
            }
        });
        executor.start(50);
        System.out.println("success count:"+atomicInteger.get());
    }

}
