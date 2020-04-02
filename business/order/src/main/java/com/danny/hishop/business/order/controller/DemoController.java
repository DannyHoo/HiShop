package com.danny.hishop.business.order.controller;

import com.danny.hishop.framework.model.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2020/4/1下午10:17
 */
@RestController
@RequestMapping("/order/demo")
@Slf4j
//开启/actuator/refresh机制 在主启动类和需要用@Value加载配置的类上加@RefreshScope注解，修改配置后，
// POST调用/actuator/refresh可以热刷新配置
// https://blog.csdn.net/jabony/article/details/99416337
@RefreshScope
public class DemoController {

    @Value("${custom.dynamicPropertyTest:这是默认值}")
    private String dynamicProperty;

    @GetMapping(value = "/refreshConfig")
    public Response getByGoodsNo() {
        /*while (true){
            try {
                Thread.sleep(1000);
                log.info("dynamicProperty:{}",dynamicProperty);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        return Response.buildSuccess(dynamicProperty);
    }
}
