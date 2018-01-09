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
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

@Deprecated
public class Consumer implements ApplicationListener<ContextRefreshedEvent>{

    @Autowired
    DefaultMQPushConsumer consumer;
    
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
                        // msg.getReconsumeTimes() 获取重新消费次数，大于15后建设另做处理并返回 ConsumeConcurrentlyStatus.CONSUME_SUCCESS
                        // do something
                        System.out.println("message:"+m);
                    } catch (final UnsupportedEncodingException e) {
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; //成功返回 
            }
        });
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        consumer();
    }

}
