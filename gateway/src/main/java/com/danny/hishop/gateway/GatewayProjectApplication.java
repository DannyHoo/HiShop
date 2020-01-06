package com.danny.hishop.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients //启用feign客户端 远程调用其他服务
@EnableHystrixDashboard //启动hystrix控制台
@EnableCircuitBreaker //启动断路器
@EnableZuulProxy //启动Zuul
public class GatewayProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayProjectApplication.class, args);
	}

}
