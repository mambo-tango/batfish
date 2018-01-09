package org.batfish.core.mqConsumer;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @author Tango
* @date 2018年1月9日 下午3:10:28
* @since 
*/
public class BatfishMessageListener implements MessageListenerConcurrently {
    
    Logger LOG = LoggerFactory.getLogger(BatfishMessageListener.class);

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
                LOG.info("message:"+m);
            } catch (final UnsupportedEncodingException e) {
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; //成功返回 
    }


}
