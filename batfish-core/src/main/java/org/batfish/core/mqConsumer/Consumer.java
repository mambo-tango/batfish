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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class Consumer implements ApplicationListener<ContextRefreshedEvent>{

    @Autowired
    DefaultMQPushConsumer consumer;
    
    @Autowired
    AnalysisTechnologyService analysisService;
    
    public void consumer() {
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                // msgs 消息list
                for (MessageExt msg : msgs) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        // 读取消息 （编码规定为UTF-8）
                        String m = new String(msg.getBody(), "UTF-8"); //json字符串
                        String result = analysisService.analysisTechnology(msg.getKeys(), m);
                        System.out.println("result=======================> " + result);
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
