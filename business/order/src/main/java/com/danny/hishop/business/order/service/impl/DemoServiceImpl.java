package com.danny.hishop.business.order.service.impl;

import com.danny.hishop.business.order.dao.OrderDAO;
import com.danny.hishop.business.order.dao.OrderDetailDAO;
import com.danny.hishop.business.order.domain.OrderDO;
import com.danny.hishop.business.order.domain.OrderDetailDO;
import com.danny.hishop.business.order.service.DemoService;
import com.danny.hishop.business.order.service.OrderDetailService;
import com.danny.hishop.framework.util.DateUtils;
import com.danny.hishop.framework.util.SpringContextUtil;
import com.danny.hishop.framework.util.snowflake.autoconfigure.core.Snowflake;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 *
 * @date 2020/3/16下午10:57
 */
@Service("demoService")
@Slf4j
public class DemoServiceImpl implements DemoService {

    @Autowired
    private Snowflake snowflake;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private OrderDetailDAO orderDetailDAO;

    @Override
    public Boolean asyncFunctionTest() {
        log.info("asyncFunctionTest start " + DateUtils.getNewFormatDateString(DateUtils.getNowDate()));
        Boolean result = SpringContextUtil.getBean(this.getClass()).asyncFunction();
        log.info("asyncFunctionTest finished " + DateUtils.getNewFormatDateString(DateUtils.getNowDate()));
        return result;
    }

    @Async("taskExecutor")
    public Boolean asyncFunction() {
        log.info("asyncFunction start " + DateUtils.getNewFormatDateString(DateUtils.getNowDate()));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("asyncFunction finished " + DateUtils.getNewFormatDateString(DateUtils.getNowDate()));
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public CreateOrderResult createOrder() {
        CreateOrderResult createOrderResult=new CreateOrderResult();

        OrderDO orderDO = new OrderDO()
                .setOrderNo(String.valueOf(snowflake.genId()))
                .setUserName("userName")
                .setReceiverName("收货人")
                .setReceiverMobileNo("13579246810")
                .setReceiverAddress("北京市海淀区")
                .setPayType(10)
                .setStatus(10)
                .setDeliverType(10)
                .setDeliverTime(10)
                .setTotalPrice(BigDecimal.TEN)
                .setCutDownPrice(BigDecimal.ZERO)
                .setFreight(BigDecimal.ONE)
                .setActualPrice(BigDecimal.TEN);

        OrderDetailDO orderDetailDO = new OrderDetailDO()
                .setOrderNo(orderDO.getOrderNo())
                .setGoodsNo("G923012389102")
                .setGoodsNum(1)
                .setTotalPrice(orderDO.getTotalPrice())
                .setActualPrice(orderDO.getActualPrice());

        //saveOrderDetail(orderDetailDO);
        //声明事务通过注解处理的切面函数处理，切面函数调用方法是通过代理。通过DemoServiceImpl的代理调用saveOrderDetail才能成功进入切面，或者通过其他类调用（调用orderDetailService.saveOrderDetail也可以）
        //orderDetailService.saveOrderDetail(orderDetailDO);
        //SpringContextUtil.getBean(this.getClass()).saveOrderDetail(orderDetailDO);
        createOrderResult.setOrderDetailDO(orderDetailDO);

        orderDAO.insertOrderDO(orderDO);
        createOrderResult.setOrderDO(orderDO);

        if (true){
            throw new RuntimeException();
        }

        return createOrderResult;
    }

    @Transactional(rollbackFor = Throwable.class,propagation= Propagation.REQUIRES_NEW)
    public OrderDetailDO saveOrderDetail(OrderDetailDO orderDetailDO) {
        orderDetailDAO.insertOrderDetailDO(orderDetailDO);
        return orderDetailDO;
    }

    @Data
    @Accessors(chain = true)
    public static class CreateOrderResult{
        private OrderDO orderDO;
        private OrderDetailDO orderDetailDO;
    }
}
