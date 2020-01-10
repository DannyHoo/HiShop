package com.danny.hishop.management.controller;

import com.alibaba.fastjson.JSON;
import com.danny.hishop.framework.model.response.Response;
import com.danny.hishop.management.model.CreateOrderParameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2020/1/10下午5:08
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping("/gateway")
    public Response gateway(HttpServletRequest request, @RequestBody CreateOrderParameter param) {
        try {
            System.out.println("request[/test/gateway] param["+JSON.toJSONString(param)+"]");
            //log.info("request[{}] param[{}]", "/test/gateway", JSON.toJSONString(param));
            Thread.sleep(new Random().nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Response.buildSuccess();
    }

}
