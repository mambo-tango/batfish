package org.batfish.core.mqConsumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.context.ApplicationEvent;

/**
* @author Tango
* @date 2018年1月10日 下午6:17:13
* @since 
*/
@Deprecated
public class ConsumerEvent extends ApplicationEvent{

    /**
     * 
     */
    private static final long serialVersionUID = -744405461307701409L;

    private DefaultMQPushConsumer consumer;
    private MessageExt messageExt;
    private String topic;
    
    public ConsumerEvent(Object source) {
        super(source);
    }
    
    public ConsumerEvent(MessageExt messageExt, DefaultMQPushConsumer consumer) {
        super(messageExt);
        this.consumer = consumer;
        this.messageExt = messageExt;
        this.topic = messageExt.getTopic();
    }
    

    
    public DefaultMQPushConsumer getConsumer() {
        return consumer;
    }
    public void setConsumer(DefaultMQPushConsumer consumer) {
        this.consumer = consumer;
    }
    public MessageExt getMessageExt() {
        return messageExt;
    }
    public void setMessageExt(MessageExt messageExt) {
        this.messageExt = messageExt;
    }
    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
    
    

}
