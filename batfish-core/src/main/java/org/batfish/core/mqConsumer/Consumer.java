package org.batfish.core.mqConsumer;
/**
* @author Tango
* @date 2018年1月9日 下午2:35:43
* @since 
*/

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.batfish.core.service.AnalysisTechnologyService;
import org.batfish.core.service.ESService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class Consumer implements ApplicationListener<ContextRefreshedEvent>{
    
    Logger LOG = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    DefaultMQPushConsumer consumer;
    
    @Autowired
    AnalysisTechnologyService analysisService;
    
    @Autowired
    ESService esService;
    
    public void consumer() {
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                // msgs 消息list
                for (MessageExt msg : msgs) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        // 读取消息 （编码规定为UTF-8）
                        String m = new String(msg.getBody(), "UTF-8"); //json字符串
                        String resultANA = analysisService.analysisTechnology(msg.getKeys(), m);
                        String resultES = esService.saveToES(msg.getKeys(), m);
                        LOG.info(">>>>>>>>> resultANA=> " + resultANA +" reultES=>" + resultES + " <<<<<<<<<<<<<<");
                    } catch (final UnsupportedEncodingException e) {
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; //成功返回 
            }
        });
        try {
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        consumer();
    }

}
