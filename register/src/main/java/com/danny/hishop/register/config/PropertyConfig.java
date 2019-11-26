package com.danny.hishop.register.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/11/19下午8:07
 */
@Slf4j
@Component
public class PropertyConfig {

    @Value("${project.property.property1}")
    private String project_property_property1;

    @PostConstruct
    public void init() {
        log.info("loadding properties:");
        log.info("project.property.property1:" + project_property_property1);
    }
}
