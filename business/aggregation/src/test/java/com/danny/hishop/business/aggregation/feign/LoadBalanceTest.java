package com.danny.hishop.business.aggregation.feign;

import com.danny.hishop.business.aggregation.AggregationApplicationTests;
import com.danny.hishop.business.aggregation.feign.user.UserService;
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
 * @date 2019/12/19下午2:21
 */
@Slf4j
public class LoadBalanceTest extends AggregationApplicationTests {
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    /*
     * Spring-SpringCloud之Ribbon和Feign实现负载均衡
     * http://antsnote.club/2019/05/20/Spring-SpringCloud之Ribbon和Feign实现负载均衡/
     */

    /**
     * 1、直接使用IP调用
     * Spring Cloud 的微服务之间是使用 HTTP 调用的方式实现的, 一个服务调用另一个服务的 HTTP 接口示例如下
     */
    @Test
    public void requestIPTest() {
        String url = "http://192.168.1.19:8080/user/address/getByUserName/" + "82Z76oIu";
        log.info("url = {}", url);
        Response<List<AddressDTO>> result = restTemplate.getForObject(url, Response.class);
        log.info("result = {}", result);
    }

    /**
     * 2、用LoadBalance实现动态IP调用
     * 不需要强制写死 IP, 只需要指定调用哪一个服务名即可实现客户端版的负载均衡
     * Ribbon和Eureka整合后Consumer可以直接调用服务而不用再关心地址和端口号
     */
    @Test
    public void loadBalancerClientTest() {
        // 指定需要调用的服务名即可完成客户端的负载均衡
        // 具体实现是获取到 Eureka 服务注册中心中注册的服务名和服务端口号, 最后通过负载均衡算法获取服务中的其中一个 IP,
        // 把 URL 中的服务名替换成 IP 后再使用 RestTemplate 实例进行调用
        ServiceInstance serviceInstance = loadBalancerClient.choose("HISHOP-USER");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/user/address/getByUserName/" + "82Z76oIu";
        log.info("url = {}", url);
        Response<List<AddressDTO>> result = restTemplate.getForObject(url, Response.class);
        log.info("result = {}", result);
    }

    /**
     * 3、用Ribbon(基于LoadBalance封装)简化调用
     * 不需要注入LoadBalanceClient，只需要在RedisTemplate上添加@LoadBalanced注解
     */
    @Test
    public void ribbonTest() {
        String url = "http://HISHOP-USER/user/address/getByUserName/" + "82Z76oIu";
        log.info("url = {}", url);
        Response<List<AddressDTO>> result = restTemplate.getForObject(url, Response.class);
        log.info("result = {}", result);
    }

    /**
     * 4、用Feign(基于Ribbon封装)进一步简化调用
     * 更接近本地调用的方式了
     */
    @Test
    public void feignTest() {
        Response<List<AddressDTO>> result = userService.getAddressByUserName("82Z76oIu");
        log.info("result = {}", result);
    }

}
