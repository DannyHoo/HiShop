package com.danny.hishop.business.aggregation.config;

import com.danny.hishop.framework.mq.rocketmq.producer.RocketMQProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RocketMQConfig {

    @Value("${rocketmq.namesrvAddr}")
    private String namesrvAddr;

    @Value("${rocketmq.producer.groupName}")
    private String producerGroupName;

    @Bean
    public RocketMQProducer rocketMQProducer() {
        DefaultMQProducer mqProducer = new DefaultMQProducer();
        mqProducer.setNamesrvAddr(namesrvAddr);
        mqProducer.setProducerGroup(producerGroupName);
        //mqProducer.setRetryTimesWhenSendFailed(10);  //消息发送失败、发送超时时重试发送消息次数
        //mqProducer.setSendMsgTimeout(10000); //超时时间
        try {
            log.info("rocketmq producer server正在启动");
            mqProducer.start();
            log.info("rocketmq producer server启动完成");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        return new RocketMQProducer().setMqProducer(mqProducer);
    }


}
