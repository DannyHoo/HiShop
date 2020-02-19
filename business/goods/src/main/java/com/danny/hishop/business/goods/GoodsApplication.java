package com.danny.hishop.business.goods;

import com.danny.hishop.framework.util.snowflake.autoconfigure.annotation.EnableSnowflake;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/11/26下午5:50
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients //启用feign客户端
@EnableHystrixDashboard //启动hystrix控制台
@EnableCircuitBreaker //启动断路器
@EnableSnowflake
public class GoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }

}
