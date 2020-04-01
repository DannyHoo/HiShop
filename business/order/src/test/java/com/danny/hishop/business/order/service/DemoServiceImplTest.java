package com.danny.hishop.business.order.service;

import com.danny.hishop.business.order.OrderApplicationTests;
import com.danny.hishop.business.order.domain.OrderDO;
import com.danny.hishop.business.order.service.impl.DemoServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @date 2020/3/16下午11:02
 */
public class DemoServiceImplTest extends OrderApplicationTests {

    @Autowired
    private DemoService demoService;

    /**
     * 异步方法测试
     * @throws InterruptedException
     */
    @Test
    public void asyncFunctionTest() throws InterruptedException {
        Boolean result = demoService.asyncFunctionTest();
        System.out.println(result);
        Thread.sleep(6000);
    }

    /**
     * 事务测试
     */
    @Test
    public void transactionTest(){
        DemoServiceImpl.CreateOrderResult createOrderResult= demoService.createOrder();
        printResult(createOrderResult);
    }
}
