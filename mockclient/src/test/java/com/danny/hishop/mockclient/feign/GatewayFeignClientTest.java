package com.danny.hishop.mockclient.feign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.danny.hishop.framework.util.RandomValueUtil;
import com.danny.hishop.framework.util.http.HttpClientUtils;
import com.danny.hishop.framework.util.test.Executor;
import com.danny.hishop.framework.util.test.ExecutorInterface;
import com.danny.hishop.mockclient.MockClientApplicationTests;
import com.google.gson.JsonObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author huyuyang
 * @date 2020/1/6下午5:41
 */
public class GatewayFeignClientTest extends MockClientApplicationTests {

    @Autowired
    private GatewayFeignClient gatewayFeignClient;
    private JSONObject getCreateOrderParam() {
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
    public void createOrderTest() throws InterruptedException {
        final AtomicInteger atomicInteger=new AtomicInteger(0);
        Executor executor = new Executor(new ExecutorInterface() {
            @Override
            public void executeJob(){
                for (int i = 0; i < 1; i++) {
                    JSONObject param = getCreateOrderParam();
                    String result = "";
                    try {
                        JSONObject jsonObject = gatewayFeignClient.createOrder(param);
                        if (jsonObject.getInteger("code")==100000){
                            atomicInteger.addAndGet(1);
                        }else{
                            System.out.println("ERROR:");
                            System.out.println(result);
                        }
                    } catch (Exception e) {
                    }
                }
            }
        });
        executor.start(1);
        System.out.println("success count:"+atomicInteger.get());

    }

    //直接访问gateway
    @Test
    public void createOrderByGatewayTest() throws InterruptedException {
        final AtomicInteger atomicInteger=new AtomicInteger(0);
        Executor executor = new Executor(new ExecutorInterface() {
            @Override
            public void executeJob(){
                for (int i = 0; i < 1; i++) {
                    JSONObject param = getCreateOrderParam();
                    String result = "";
                    try {
                        result= HttpClientUtils.doPost("http://10.249.254.246:8200/api/aggregation/order/create", param, 180000);
                        printResult(result);
                        JSONObject jsonObject= JSON.parseObject(result);
                        if (jsonObject.getInteger("code")==100000){
                            atomicInteger.addAndGet(1);
                        }else{
                            System.out.println("ERROR:");
                            System.out.println(result);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        executor.start(100);
        System.out.println("success count:"+atomicInteger.get());
    }

    //直接访问aggregation
    //select (select count(*) from hishop_order.t_order) as orderNum ,(select count(*) from hishop_order.t_order_detail) as orderDetailNum
    //SELECT t.create_second,count(t.id) FROM(SELECT t1.id,t1.createTime,DATE_FORMAT(t1.createTime,'%Y-%m-%d %H:%i:%S') create_second FROM hishop_order.t_order t1 ORDER BY t1.createTime ) AS t GROUP BY t.create_second ORDER BY t.create_second DESC;
    @Test
    public void createOrderByAggregationTest() throws InterruptedException {
        final AtomicInteger atomicInteger=new AtomicInteger(0);
        Executor executor = new Executor(new ExecutorInterface() {
            @Override
            public void executeJob(){
                for (int i = 0; i < 1; i++) {
                    JSONObject param = getCreateOrderParam();
                    String result = "";
                    try {
                        result= HttpClientUtils.doPost("http://192.168.8.103:8311/order/create", param, 180000);
                        printResult(result);
                        JSONObject jsonObject= JSON.parseObject(result);
                        if (jsonObject.getInteger("code")==100000){
                            atomicInteger.addAndGet(1);
                        }else{
                            System.out.println("ERROR:");
                            System.out.println(result);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        executor.start(500);
        System.out.println("success count:"+atomicInteger.get());
    }

    @Test
    public void createOrderByAggregationLoopTest() throws InterruptedException {
        final AtomicInteger atomicInteger=new AtomicInteger(0);
        for (int i = 0; i < 100000; i++) {
            JSONObject param = getCreateOrderParam();
            String result = "";
            try {
                result= HttpClientUtils.doPost("http://192.168.8.103:8311/order/create", param, 180000);
                printResult(result);
                JSONObject jsonObject= JSON.parseObject(result);
                if (jsonObject.getInteger("code")==100000){
                    atomicInteger.addAndGet(1);
                }else{
                    System.out.println("ERROR:");
                    System.out.println(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("success count:"+atomicInteger.get());
        }
    }

    @Test
    public void createOrderRandomByAggregationTest() throws InterruptedException {
        final AtomicInteger atomicInteger=new AtomicInteger(0);
        while (true){
            new Executor(new ExecutorInterface() {
                @Override
                public void executeJob(){
                    for (int i = 0; i < 1; i++) {
                        JSONObject param = getCreateOrderParam();
                        String result = "";
                        try {
                            result= HttpClientUtils.doPost("http://192.168.8.103:8311/order/create", param, 180000);
                            printResult(result);
                            JSONObject jsonObject= JSON.parseObject(result);
                            if (jsonObject.getInteger("code")==100000){
                                atomicInteger.addAndGet(1);
                            }else{
                                System.out.println("ERROR:");
                                System.out.println(result);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start(RandomValueUtil.randomIntValue(50,100));
            System.out.println("success count:"+atomicInteger.get());
        }
    }


    @Test
    public void requestManagementTest() throws InterruptedException {
        final AtomicInteger atomicInteger=new AtomicInteger(0);
        Executor executor = new Executor(new ExecutorInterface() {
            @Override
            public void executeJob(){
                for (int i = 0; i < 1; i++) {
                    JSONObject param = getCreateOrderParam();
                    JSONObject riskParam=new JSONObject();
                    riskParam.put("request_id","20ij2ef9j20ef92jf02");
                    riskParam.put("group_code","CO-10000001");
                    riskParam.put("user_id","999");
                    riskParam.put("user_device_id","32023j23234o23");
                    String result = "";
                    try {
                        result= HttpClientUtils.doPost("http://10.249.254.246:8200/api/management/test/gateway", param, 180000);
                        //result= HttpClientUtils.doPost("http://10.249.201.159:8200/api/management/test/gateway", param, 180000);
                        //result= HttpClientUtils.doPost("http://localhost:8070/opos/rc/verify", riskParam, 180000);
                        printResult(result);
                        JSONObject jsonObject= JSON.parseObject(result);
                        if (jsonObject.getInteger("code")==200){
                            atomicInteger.addAndGet(1);
                        }else{
                            System.out.println("ERROR:");
                            System.out.println(result);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        executor.start(400);
        System.out.println("success count:"+atomicInteger.get());
    }

}
