/*
 * Copyright © 2015-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.batfish.core;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

/**
 * push消费模式demo
 */
/*
rocketmq依赖
<dependency>
<groupId>org.apache.rocketmq</groupId>
<artifactId>rocketmq-client</artifactId>
<version>4.1.0-incubating</version>
</dependency>
<dependency>
<groupId>org.apache.rocketmq</groupId>
<artifactId>rocketmq-common</artifactId>
<version>4.1.0-incubating</version>
</dependency>
<dependency>
<groupId>ch.qos.logback</groupId>
<artifactId>logback-classic</artifactId>
<version>1.1.1</version>
</dependency>
<dependency>
<groupId>ch.qos.logback</groupId>
<artifactId>logback-core</artifactId>
<version>1.1.1</version>
</dependency>
*/
// 注意：消息有可能会重复被消费，所以得保持业务逻辑保持幂等性或业务去重

//生产环境topic按操作类揽收、容器、派件、签收、单据、留仓和到发车7个；如下
//rs-topic-taking
//rs-topic-container
//rs-topic-handon
//rs-topic-signature
//rs-topic-stay
//rs-topic-bill
//rs-topic-truck
//message tags为操作码，如311 、 310；keys 为id字段值，数据 body为 UTF-8 编码 byte[]

/**
 * @author xiebanghua 2017年6月27日 上午9:50:02
 * @since 0.0.1
 */
public final class PushConsumerDemo {
    private PushConsumerDemo() {
    }
    /**
     * @param args args
     * @throws Exception Exception
     */
    public static void main(final String[] args) throws Exception {
     // 消费组名设置， 同属一个应用设为同一个组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("rs-consumer-split-container");
        // rokcetmq 集群broker ip:port;ip:prot...（测试环境：""）
        consumer.setNamesrvAddr("10.1.xxx.xx:9876;10.1.xxx.xx:9876");
        // set instanceName 可不设
        consumer.setInstanceName("testInstanceName");
        // set消费最小线程数， 默认20
//        consumer.setConsumeThreadMin(20);
        // set消费最大线程数， 默认64
//        consumer.setConsumeThreadMax(64);
        consumer.setMessageModel(MessageModel.CLUSTERING);
        // 一次消费消息数(受server配置限制),默认为1, 建设使用单条便于重新消费
//        consumer.setConsumeMessageBatchMaxSize(32);
        // 每次拉取消息数据(受server配置限制),默认为 32
//        consumer.setPullBatchSize(32);
//        拉消息间隔，由于是长轮询，所以为0，但是如果应用为了流控，也可以设置大于0的值，单位毫秒
//        consumer.setPullInterval(0);
        // 持久化Consumer消费进度到mq上间隔时间，单位毫秒; 默认 5 * 1000
//        consumer.setPersistConsumerOffsetInterval(5 * 1000);
        
        // 订阅topic所有tag
        consumer.subscribe("rs-topic-handon", "*");
        /**
         * 订阅指定topic下所有消息<br>
         * 注意：一个consumer对象可以订阅多个tag
         * tag值为opCode值
         */
        // 订阅topic其中某些tag，用“ || ” 分开
//        consumer.subscribe(topic, "310 || 311 || 171");
//      一个consumer 可以订阅多个topic， 如下再加一个；建议一个consumer 只订阅一个topic，更方便管理
//        consumer.subscribe("TopicTest2","*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(final List<MessageExt> msgs, final ConsumeConcurrentlyContext context) {
                // msgs 消息list
                for (MessageExt msg : msgs) {
                    try {
                        // 读取消息 （编码规定为UTF-8）
                        String m = new String(msg.getBody(), "UTF-8"); //json字符串
                        // msg.getReconsumeTimes() 获取重新消费次数，大于15后建设另做处理并返回 ConsumeConcurrentlyStatus.CONSUME_SUCCESS
                        // do something
                    } catch (final UnsupportedEncodingException e) {
                    }
                }
//                return ConsumeConcurrentlyStatus.RECONSUME_LATER;// 需要重新消费(整个msgs List信息); 
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; //成功返回 
            }
        });
        consumer.start();
    }
}


