package com.danny.hishop.business.aggregation.feign;

import com.danny.hishop.business.aggregation.AggregationApplicationTests;
import com.danny.hishop.business.aggregation.model.user.dto.AddressDTO;
import com.danny.hishop.framework.model.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/12/19下午2:21
 */
@Slf4j
public class LoadBalanceTest extends AggregationApplicationTests {
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void loadBalancerClientTest() {
        // 指定需要调用的服务名即可完成客户端的负载均衡
        ServiceInstance serviceInstance = loadBalancerClient.choose("HISHOP-USER");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/user/address/getByUserName/"+"82Z76oIu";
        log.info("url = {}", url);
        Response<List<AddressDTO>> result = restTemplate.getForObject(url, Response.class);
        log.info("result = {}", result);
    }
}
