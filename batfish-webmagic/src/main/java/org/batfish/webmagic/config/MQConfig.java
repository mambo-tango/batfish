package org.batfish.webmagic.config;
/**
* @author Tango
* @date 2017年12月7日 上午10:47:52
* @since 
*/

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
    
    @Bean
    public DefaultMQProducer initProducer() {
        DefaultMQProducer producer = new DefaultMQProducer("testGruop");
        producer.setNamesrvAddr("10.1.237.101:9876;10.1.237.102:9876");
        producer.setSendMsgTimeout(8000);
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        return producer;
    }
}
