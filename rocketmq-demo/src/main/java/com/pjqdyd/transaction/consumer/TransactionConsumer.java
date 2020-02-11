package com.pjqdyd.transaction.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;

/**   
 * @Description:  [事务消息消费者]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class TransactionConsumer {

    public static void main(String[] args) throws Exception{
        //1. 创建消息消费者, 制定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group2");
        consumer.setNamesrvAddr("192.168.141.129:9876");
        //3.订阅主题Topic和Tag
        consumer.subscribe("transaction", "tag1");

        //4.设置回调函数处理消息
        consumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
            list.stream().forEach(msg -> System.out.println(new String(msg.getBody())));
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; //消费完后,返回成功
        });

        //5.启动消费者Consumer
        consumer.start();
    }

}
