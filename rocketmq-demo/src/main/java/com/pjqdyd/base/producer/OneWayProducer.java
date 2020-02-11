package com.pjqdyd.base.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**   
 * @Description:  [发送单向消息, 无需关心发送结果, 如日志]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class OneWayProducer {

    public static void main(String[] args) throws Exception {
        //1.创建消息生产者Producer, 并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("group1");

        producer.setNamesrvAddr("192.168.141.129:9876"); //2.指定NameServer地址 (宿主机ip:9876)

        producer.start();//3.启动Producer

        //4.创建消息对象, 指定Topic主题, Tag, 消息体
        for (int i = 0; i < 10; i++){
            Message message = new Message("base", "tag3", ("Hello World " + i).getBytes());
            //5.发送单向消息
            producer.sendOneway(message);

            TimeUnit.SECONDS.sleep(1);
        }

        producer.shutdown(); //6.关闭生产者Producer
    }

}
