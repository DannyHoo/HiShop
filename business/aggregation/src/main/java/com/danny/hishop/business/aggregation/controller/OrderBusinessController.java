package com.danny.hishop.business.aggregation.controller;

import com.danny.hishop.business.aggregation.model.order.param.CreateOrderParameter;
import com.danny.hishop.business.aggregation.service.OrderBusinessService;
import com.danny.hishop.framework.model.response.Response;
import com.danny.hishop.framework.model.result.ServiceResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/12/28上午11:12
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderBusinessController {

    @Autowired
    private OrderBusinessService orderBusinessService;

    /**
     * 同步下单
     *
     * @param httpServletRequest
     * @param createOrderParameter
     * @return
     */
    @RequestMapping("/create/sync")
    public Response createOrderSync(HttpServletRequest httpServletRequest, CreateOrderParameter createOrderParameter) {
        AsyncContext asyncContext = httpServletRequest.getAsyncContext();
        ServiceResult result = orderBusinessService.createOrder(createOrderParameter);
        return Response.buildSuccess();
    }


    /**
     * 异步下单
     *
     * @param httpServletRequest
     * @param createOrderParameter
     * @return
     */
    @RequestMapping("/create/async")
    public Response createOrder(HttpServletRequest httpServletRequest, CreateOrderParameter createOrderParameter) {
        AsyncContext asyncContext = httpServletRequest.getAsyncContext();
        asyncContext.addListener(new AsyncListener() {

            @Override
            public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
                log.info("执行开始");
                ServiceResult result = orderBusinessService.createOrder(createOrderParameter);
                log.info("执行结束");
            }

            @Override
            public void onComplete(AsyncEvent asyncEvent) throws IOException {
                log.info("执行完成");
                //这里可以做一些清理资源的操作...
            }

            @Override
            public void onTimeout(AsyncEvent asyncEvent) throws IOException {
                log.info("执行超时");
            }

            @Override
            public void onError(AsyncEvent asyncEvent) throws IOException {
                log.info("执行异常");
            }

        });
        asyncContext.setTimeout(2*1000);
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("内部线程：" + Thread.currentThread().getName());
                    asyncContext.getResponse().setCharacterEncoding("utf-8");
                    asyncContext.getResponse().setContentType("text/html;charset=UTF-8");
                    asyncContext.getResponse().getWriter().println("这是异步的请求返回");
                } catch (Exception e) {
                    System.out.println("异常："+e);
                }
                //异步请求完成通知
                //此时整个请求才完成
                asyncContext.complete();
            }
        });
        //此时之类 request的线程连接已经释放了
        System.out.println("主线程：" + Thread.currentThread().getName());

        return Response.buildSuccess();
    }

}
