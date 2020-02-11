package com.pjqdyd.order.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**   
 * @Description:  [顺序消息的消费]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class OrderConsumer {

    public static void main(String[] args) throws Exception{
        //1. 创建消息消费者, 制定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group1");

        consumer.setNamesrvAddr("192.168.141.129:9876");//2.指定NameServer地址

        //3.订阅主题Topic和Tag, 多个tag可以写成"tag1 || tag2", 或者 "*"
        consumer.subscribe("order", "tag1");

        //对于一个队列的消息, 会采用一个线程去处理
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                list.stream().forEach(msg -> System.out.println(new String(msg.getBody())));
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        consumer.start();
    }

}
