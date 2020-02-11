package com.pjqdyd.filter.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**   
 * @Description:  [基于标签过滤消息的生产者]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class TagFilterProducer {

    public static void main(String[] args) throws Exception {
        //1.创建消息生产者Producer, 并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("group1");

        producer.setNamesrvAddr("192.168.141.129:9876");

        producer.start();//3.启动Producer

        //4.创建消息对象, 指定Topic主题, Tag, 消息体
        for (int i = 0; i < 10; i++){
            Message message = new Message("tag-filter", "tag1", ("Hello World " + i).getBytes());
            //5.发送消息
            SendResult result = producer.send(message);
            System.out.println("发送状态:" + result.getSendStatus());

            TimeUnit.SECONDS.sleep(1);
        }

        producer.shutdown(); //6.关闭生产者Producer
    }


}
