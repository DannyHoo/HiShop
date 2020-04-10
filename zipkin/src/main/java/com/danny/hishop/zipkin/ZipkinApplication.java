package com.danny.hishop.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin2.server.internal.EnableZipkinServer;
/**
 * logPath
 *
 * @date 2020/4/1下午4:47
 */
@SpringBootApplication(scanBasePackages = {"com.danny.hishop"})
@EnableZipkinServer
@EnableEurekaClient
public class ZipkinApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinApplication.class, args);
    }

}
