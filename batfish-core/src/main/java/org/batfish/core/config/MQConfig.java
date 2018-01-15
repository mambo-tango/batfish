package org.batfish.core.config;
/**
* @author Tango
* @date 2017年12月7日 下午1:54:31
* @since 
*/

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.impl.consumer.ConsumeMessageConcurrentlyService;
import org.apache.rocketmq.common.message.MessageExt;
import org.batfish.core.mqConsumer.ConsumerEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class MQConfig {
    
    Logger LOG = LoggerFactory.getLogger(MQConfig.class);
    
    @Autowired
    Environment environment;
    
    @Autowired
    private ApplicationEventPublisher publisher;

    @Bean
    public DefaultMQPushConsumer pushConsumer() {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(environment.getProperty("rocketmq.consumer.groupName"));
        consumer.setNamesrvAddr(environment.getProperty("rocketmq.consumer.nameServer"));
        try {
            consumer.subscribe(environment.getProperty("rocketmq.consumer.topic"), environment.getProperty("rocketmq.consumer.tag"));
            consumer.setConsumeTimeout(Integer.valueOf(environment.getProperty("rocketmq.consumer.timeout")));
            consumer.setPullBatchSize(10);
            
//            consumer.registerMessageListener(new MessageListenerConcurrently() {
//                
//                @Override
//                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
//                    // msgs 消息list
//                    for (MessageExt msg : msgs) {
//                        try {
//                            publisher.publishEvent(new ConsumerEvent(msg, consumer));
//                        } catch (final Exception e) {
//                            LOG.error(e.getMessage(), e);
//                            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
//                        }
//                    }
//                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//                }
//            });
//            new Thread(() -> {
//                try {
//                    Thread.sleep(5000);
//                    try {
//                        consumer.start();
//                    } catch (MQClientException e) {
//                        e.printStackTrace();
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//        }).start();
            
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        return consumer;
    }
}
