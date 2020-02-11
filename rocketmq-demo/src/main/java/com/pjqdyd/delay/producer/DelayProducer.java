package com.pjqdyd.delay.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**   
 * @Description:  [延迟消息生产者]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class DelayProducer {

    public static void main(String[] args) throws Exception {
        //1.创建消息生产者Producer, 并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        producer.setNamesrvAddr("192.168.141.129:9876");
        producer.start();//3.启动Producer

        //4.创建消息对象, 指定Topic主题, Tag, 消息体
        for (int i = 0; i < 10; i++){
            Message message = new Message("delay", "tag1", ("Hello World " + i).getBytes());
            message.setDelayTimeLevel(2); //设置延迟级别
            //5.发送消息
            SendResult result = producer.send(message);
            System.out.println("发送状态:" + result.getSendStatus());
        }

        producer.shutdown(); //6.关闭生产者Producer
    }


}
