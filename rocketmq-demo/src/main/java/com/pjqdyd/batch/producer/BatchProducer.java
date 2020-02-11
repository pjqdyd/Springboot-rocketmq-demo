package com.pjqdyd.batch.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description:  [批量发送消息]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 *  
 */

public class BatchProducer {

    public static void main(String[] args) throws Exception {
        //1.创建消息生产者Producer, 并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        producer.setNamesrvAddr("192.168.141.129:9876");
        producer.start();

        //构建消息集合
        List<Message> messageList = new ArrayList<>();

        Message message1 = new Message("batch", "tag1", ("Hello World 1").getBytes());
        Message message2 = new Message("batch", "tag1", ("Hello World 2").getBytes());
        Message message3 = new Message("batch", "tag1", ("Hello World 3").getBytes());

        messageList.add(message1);
        messageList.add(message2);
        messageList.add(message3);

        //5.发送批量消息
        SendResult result = producer.send(messageList);

        System.out.println("发送状态:" + result.getSendStatus());
        TimeUnit.SECONDS.sleep(1);

        producer.shutdown(); //6.关闭生产者Producer
    }


}
