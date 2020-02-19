package com.danny.hishop.gateway.controller;

import com.alibaba.fastjson.JSON;
import com.danny.hishop.framework.model.response.Response;
import com.danny.hishop.framework.model.result.ServiceResult;
import com.danny.hishop.gateway.service.AsyncServiceDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * SpringBoot中异步请求和异步调用 https://www.cnblogs.com/baixianlong/p/10661591.html
 *
 * @author huyuyang
 * @date 2020/1/6下午9:45
 */
@RestController
@RequestMapping("/demo")
@Slf4j
public class AsyncRequestDemoController {

    @Autowired
    private AsyncServiceDemoService asyncServiceDemoService;

    /**
     * 方式一：Servelt实现异步请求
     *
     * @param httpServletRequest
     * @param createOrderParameter
     * @return
     */
    @GetMapping("/async/request/1")
    public void asyncRequest1(HttpServletRequest httpServletRequest) {
        httpServletRequest.startAsync();
        if (httpServletRequest.isAsyncSupported()) {
            AsyncContext asyncContext = httpServletRequest.getAsyncContext();
            asyncContext.addListener(new AsyncListener() {

                @Override
                public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
                    log.info("onStartAsync 执行开始");
                }

                @Override
                public void onComplete(AsyncEvent asyncEvent) throws IOException {
                    log.info("onComplete 执行完成");
                    //这里可以做一些清理资源的操作...
                }

                @Override
                public void onTimeout(AsyncEvent asyncEvent) throws IOException {
                    log.info("onTimeout 执行超时");
                }

                @Override
                public void onError(AsyncEvent asyncEvent) throws IOException {
                    log.info("onError 执行异常");
                }

            });
            //设置超时时间
            asyncContext.setTimeout(10 * 1000);
            asyncContext.start(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("内部线程：" + Thread.currentThread().getName());
                        ServiceResult serviceResult = asyncServiceDemoService.asyncRequest();
                        ServletResponse servletResponse = asyncContext.getResponse();
                        servletResponse.setCharacterEncoding("utf-8");
                        servletResponse.setContentType("application/json;charset=UTF-8");
                        Response responseResult = Response.build(serviceResult);
                        servletResponse.getWriter().println(JSON.toJSONString(responseResult));
                    } catch (Exception e) {
                        System.out.println("异常：" + e);
                        e.printStackTrace();
                    }
                    //异步请求完成通知
                    //此时整个请求才完成
                    asyncContext.complete();
                }
            });
            //此时之类 request的线程连接已经释放了
            System.out.println("主线程：" + Thread.currentThread().getName());
        }
    }

    /**
     * 方式二：返回的参数包裹一层callable，可以继承WebMvcConfigurerAdapter类来设置默认线程池和超时处理
     *
     * @param httpServletRequest
     * @param createOrderParameter
     * @return
     */
    @GetMapping("/async/request/2")
    public Callable<Response> asyncRequest2(HttpServletRequest httpServletRequest) {
        log.info("外部线程：", Thread.currentThread().getName());
        return new Callable<Response>() {
            @Override
            public Response call() throws Exception {
                ServiceResult serviceResult = asyncServiceDemoService.asyncRequest();
                Response responseResult = Response.build(serviceResult);
                return responseResult;
            }
        };
    }


    /**
     * 方式二：返回的参数包裹一层callable，可以继承WebMvcConfigurerAdapter类来设置默认线程池和超时处理
     *
     * @param httpServletRequest
     * @param createOrderParameter
     * @return
     */
    @GetMapping("/async/request/3")
    public WebAsyncTask<Response> asyncRequest3(HttpServletRequest httpServletRequest) {
        log.info("外部线程开始：", Thread.currentThread().getName());
        Callable callable = new Callable<Response>() {
            @Override
            public Response call() throws Exception {
                log.info("内部线程开始：", Thread.currentThread().getName());
                ServiceResult serviceResult = asyncServiceDemoService.asyncRequest();
                Response responseResult = Response.build(serviceResult);
                log.info("内部线程结束：", Thread.currentThread().getName());
                return responseResult;
            }
        };

        WebAsyncTask<Response> webAsyncTask = new WebAsyncTask<>(10 * 1000, callable);
        webAsyncTask.onTimeout(new Callable<Response>() {
            @Override
            public Response call() throws Exception {
                log.info("超时：", Thread.currentThread().getName());
                return Response.build(500, "超时");
            }
        });
        return webAsyncTask;
    }


}
