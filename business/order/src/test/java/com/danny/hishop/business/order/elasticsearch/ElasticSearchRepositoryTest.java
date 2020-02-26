package com.danny.hishop.business.order.elasticsearch;

import com.danny.hishop.business.order.OrderApplicationTests;
import com.danny.hishop.business.order.elasticsearch.OrderDocument;
import com.danny.hishop.business.order.elasticsearch.OrderEsRepository;
import com.danny.hishop.framework.util.DateUtils;
import com.danny.hishop.framework.util.RandomValueUtil;
import com.danny.hishop.framework.util.StringUtil;
import com.danny.hishop.framework.util.snowflake.autoconfigure.core.Snowflake;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ElasticSearchRepositoryTest extends OrderApplicationTests {

    @Autowired
    private Snowflake snowflake;

    @Autowired
    private OrderEsRepository orderEsRepository;

    /**
     * 插入数据
     * 根据ID查询数据，返回实体
     */
    @Test
    public void saveTest() {
        OrderDocument orderInsertData = getOrder();
        OrderDocument orderInsertResult = orderEsRepository.save(orderInsertData);
        Optional<OrderDocument> orderQueryResult = Optional.empty();
        if (orderEsRepository.existsById(orderInsertData.getId())) {
            orderQueryResult = orderEsRepository.findById(orderInsertData.getId());
            if (orderQueryResult.isPresent()) {
                printResult("结果为：", orderQueryResult.get());
            }
        }
        Assert.assertNotNull(orderQueryResult.get());
    }


    /**
     * 根据字段查询数据，返回列表
     */
    @Test
    public void queryTest() {
        String orderName = "DannyHoo";
        String mobileNo = "13579246810";
        String idCardNo = "130900199004091109";
        String realName = "胡丹尼";
        String comment = "ElasticSearch测试";
        List<OrderDocument> orderList1 = orderEsRepository.findByUserName(orderName);
        List<OrderDocument> orderList2 = orderEsRepository.findByOrderNo(mobileNo);
        List<OrderDocument> orderList3 = orderEsRepository.findByReceiverName(idCardNo);
        List<OrderDocument> orderList4 = orderEsRepository.findByReceiverMobileNo(realName);
        List<OrderDocument> orderList5 = orderEsRepository.findByReceiverAddress(comment);
        printResult(orderList1);
        printResult(orderList2);
        printResult(orderList3);
        printResult(orderList4);
        printResult(orderList5);
    }

    private OrderDocument getOrder() {
        OrderDocument orderDocument = new OrderDocument();
        orderDocument.setOrderNo("O" + snowflake.genId() + StringUtil.getRandomNum(5))
                .setUserName(StringUtil.getStringRandom(8))
                .setReceiverName(RandomValueUtil.getAddress().get("name").toString())
                .setReceiverMobileNo("1" + StringUtil.getRandomNum(10))
                .setReceiverAddress(RandomValueUtil.getAddress().get("road").toString())
                .setPayType(10)
                .setStatus(10)
                .setDeliverType(10)
                .setDeliverTime(20)
                .setTotalPrice(BigDecimal.valueOf(5800))
                .setCutDownPrice(BigDecimal.valueOf(5600))
                .setFreight(BigDecimal.valueOf(12))
                .setActualPrice(BigDecimal.valueOf(5200))
                .setId(snowflake.genId())
                .setComment("测试订单")
                .setCreateTime(DateUtils.getNowDate())
                .setUpdateTime(DateUtils.getNowDate());
        return orderDocument;
    }


}
