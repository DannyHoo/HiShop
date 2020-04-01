package com.danny.hishop.business.aggregation;

import com.danny.hishop.framework.util.snowflake.autoconfigure.annotation.EnableSnowflake;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author huyuyang
 * @date 2019/11/26下午5:50
 */
@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
@EnableEurekaClient
@EnableFeignClients //启用feign客户端 远程调用其他服务
@EnableHystrixDashboard //启动hystrix控制台
@EnableCircuitBreaker //启动断路器
@EnableSnowflake
@EnableHystrix
@ComponentScan(basePackages = "com.danny.hishop")
public class AggregationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AggregationApplication.class, args);
    }

}
