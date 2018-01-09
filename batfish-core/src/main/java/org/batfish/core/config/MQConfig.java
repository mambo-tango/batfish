package org.batfish.core.config;
/**
* @author Tango
* @date 2017年12月7日 下午1:54:31
* @since 
*/

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.batfish.core.mqConsumer.BatfishMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class MQConfig {
    
    @Autowired
    Environment environment;

    @Bean
    public DefaultMQPushConsumer pushConsumer() {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(environment.getProperty("rocketmq.consumer.groupName"));
        consumer.setNamesrvAddr(environment.getProperty("rocketmq.consumer.nameServer"));
        BatfishMessageListener msgListener = new BatfishMessageListener();
        consumer.registerMessageListener(msgListener);
        try {
            consumer.subscribe(environment.getProperty("rocketmq.consumer.topic"), environment.getProperty("rocketmq.consumer.tag"));
            consumer.setConsumeTimeout(Integer.valueOf(environment.getProperty("rocketmq.consumer.timeout")));
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        return consumer;
    }
}
