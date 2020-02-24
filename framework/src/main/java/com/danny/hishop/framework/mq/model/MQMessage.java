package com.danny.hishop.framework.mq.model;

public class MQMessage {
    private MQTopicAndTag mqTopicAndTag;
    private Object bizValue;
    /* 消息唯一标识，一般为业务唯一标识如流水号等，方便定位消息丢失问题 */
    private String msgUniqueNo;

    public MQTopicAndTag getMqTopicAndTag() {
        return mqTopicAndTag;
    }

    public MQMessage setMqTopicAndTag(MQTopicAndTag mqTopicAndTag) {
        this.mqTopicAndTag = mqTopicAndTag;
        return this;
    }

    public Object getBizValue() {
        return bizValue;
    }

    public MQMessage setBizValue(Object bizValue) {
        this.bizValue = bizValue;
        return this;
    }

    public String getMsgUniqueNo() {
        return msgUniqueNo;
    }

    public MQMessage setMsgUniqueNo(String msgUniqueNo) {
        this.msgUniqueNo = msgUniqueNo;
        return this;
    }
}
