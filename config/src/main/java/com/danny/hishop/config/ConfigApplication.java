package com.danny.hishop.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/11/14下午9:00
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class,args);
    }

}
