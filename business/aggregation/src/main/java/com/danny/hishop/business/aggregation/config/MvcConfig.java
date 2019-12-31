package com.danny.hishop.business.aggregation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/12/31下午6:09
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Resource
    private ThreadPoolTaskExecutor myThreadPoolTaskExecutor;

    /**
     * 设置Controller异步请求默认线程池和超时处理
     *
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(final AsyncSupportConfigurer configurer) {
        //处理 callable超时
        configurer.setDefaultTimeout(60 * 1000);
        configurer.setTaskExecutor(myThreadPoolTaskExecutor);
        configurer.registerCallableInterceptors(timeoutCallableProcessingInterceptor());
    }

    @Bean
    public TimeoutCallableProcessingInterceptor timeoutCallableProcessingInterceptor() {
        return new TimeoutCallableProcessingInterceptor();
    }

}
