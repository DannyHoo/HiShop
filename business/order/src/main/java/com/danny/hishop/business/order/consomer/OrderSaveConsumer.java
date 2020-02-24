package com.danny.hishop.business.order.consomer;

import com.alibaba.fastjson.JSON;
import com.danny.hishop.business.order.model.dto.OrderDTO;
import com.danny.hishop.business.order.model.param.OrderParameter;
import com.danny.hishop.business.order.service.OrderService;
import com.danny.hishop.framework.model.result.ServiceResult;
import com.danny.hishop.framework.mq.model.enums.MQTopicEnum;
import com.danny.hishop.framework.mq.rocketmq.consumer.BaseMQConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class OrderSaveConsumer extends BaseMQConsumer {

    private final String group = MQTopicEnum.ORDER_SAVE.getGroup();
    private final String topic = MQTopicEnum.ORDER_SAVE.getTopic();
    private final String tag = MQTopicEnum.ORDER_SAVE.getTag();

    @Value("${rocketmq.namesrvAddr}")
    private String namesrvAddr;

    @Autowired
    private OrderService orderService;

    public OrderSaveConsumer() {
    }

    @PostConstruct
    public void init() {
        try {
            setGroupAndNameSrvAddr(group, namesrvAddr);
            listen(MQTopicEnum.ORDER_SAVE.getTopic(), MQTopicEnum.ORDER_SAVE.getTag());
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean doConsume(Object messageContent) {
        OrderParameter orderParameter = null;
        System.out.println("consume succeed:" + messageContent);
        return true;
        /*try{
            orderParameter = (OrderParameter) messageContent;
            ServiceResult<OrderDTO> result = orderService.saveOrder(orderParameter);
            if (result.isSuccess()) {
                System.out.println("订单入库成功");
                return true;
            } else {
                System.out.println("订单入库失败:"+ JSON.toJSONString(orderParameter));
                return false;
            }
        }catch (Exception e){
            System.out.println("订单入库失败:"+ JSON.toJSONString(orderParameter));
            return false;
        }*/
    }

}
