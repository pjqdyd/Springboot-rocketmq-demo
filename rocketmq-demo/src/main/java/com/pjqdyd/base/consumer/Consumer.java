package com.pjqdyd.base.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**   
 * @Description:  [消息消费者]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class Consumer {

    public static void main(String[] args) throws Exception{
        //1. 创建消息消费者, 制定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group1");

        consumer.setNamesrvAddr("192.168.141.129:9876");//2.指定NameServer地址

        //3.订阅主题Topic和Tag
        consumer.subscribe("base", "tag2");

        //4.设置回调函数处理消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                list.stream().forEach(msg -> System.out.println(new String(msg.getBody())));
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; //消费完后,返回成功
            }
        });

        //5.启动消费者Consumer
        consumer.start();
    }

}
