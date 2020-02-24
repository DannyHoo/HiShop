package com.danny.hishop.business.aggregation.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.danny.hishop.business.aggregation.AggregationApplicationTests;
import com.danny.hishop.business.aggregation.model.order.param.OrderParameter;
import com.danny.hishop.framework.mq.model.MQMessage;
import com.danny.hishop.framework.mq.model.MQSendResult;
import com.danny.hishop.framework.mq.model.enums.MQTopicEnum;
import com.danny.hishop.framework.mq.rocketmq.producer.RocketMQProducer;
import com.danny.hishop.framework.util.RandomValueUtil;
import com.danny.hishop.framework.util.test.Executor;
import com.danny.hishop.framework.util.test.ExecutorInterface;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class MQTest extends AggregationApplicationTests {

    @Autowired
    private RocketMQProducer mqProducer;

    @Test
    public void sendMessageTest() throws InterruptedException, IOException, RemotingException, MQClientException, MQBrokerException {
        final AtomicInteger atomicInteger=new AtomicInteger(0);

        for (int i=0;i<1000;i++){

            Executor executor = new Executor(new ExecutorInterface() {
                @Override
                public void executeJob(){
                    MQMessage mqMessage = new MQMessage()
                            .setMqTopicAndTag(MQTopicEnum.ORDER_SAVE)
                            .setBizValue(JSON.toJSONString(new Order().setOrderNo("Order_"+atomicInteger.addAndGet(1)).setStatus("SUCCESS")));
                    MQSendResult mqSendResult = null;
                    try {
                        mqSendResult = mqProducer.sendMessage(mqMessage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    printResult("send succeed:",mqSendResult);
                }
            });
            executor.start(RandomValueUtil.getNum(1,5));
            Thread.currentThread().sleep(1500);
            System.out.println();
        }
    }

    @Test
    public void consumeMessageTest() throws InterruptedException, IOException, RemotingException, MQClientException, MQBrokerException {

    }


    public static class Order implements Serializable {
        private String orderNo;
        private String status;

        public String getOrderNo() {
            return orderNo;
        }

        public Order setOrderNo(String orderNo) {
            this.orderNo = orderNo;
            return this;
        }

        public String getStatus() {
            return status;
        }

        public Order setStatus(String status) {
            this.status = status;
            return this;
        }
    }
}
