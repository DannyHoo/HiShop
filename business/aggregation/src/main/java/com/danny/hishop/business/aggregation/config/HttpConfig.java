package com.danny.hishop.business.aggregation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/12/19下午2:35
 */
@Configuration
public class HttpConfig {

    @Autowired
    private RestTemplateBuilder builder;

    /**
     * 注入RestTemplate
     *
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        return builder.build();
    }

}
