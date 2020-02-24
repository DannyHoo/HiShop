package com.danny.hishop.framework.mq.model;

import org.apache.rocketmq.client.producer.SendResult;

public class MQSendResult {
    private SendResult sendResult;

    public SendResult getSendResult() {
        return sendResult;
    }

    public MQSendResult setSendResult(SendResult sendResult) {
        this.sendResult = sendResult;
        return this;
    }
}
