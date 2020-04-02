package com.danny.hishop.business.order;

import com.danny.hishop.framework.util.snowflake.autoconfigure.annotation.EnableSnowflake;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author huyuyang
 * @date 2019/11/26下午5:50
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients //启用feign客户端
@EnableHystrixDashboard //启动hystrix控制台
@EnableCircuitBreaker //启动断路器
@EnableSnowflake
@EnableScheduling
@EnableAsync //貌似没有也可以？
@RefreshScope //开启actuator的/refresh机制
@ComponentScan(basePackages = {"com.danny.hishop.business.order","com.danny.hishop.framework"})
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
