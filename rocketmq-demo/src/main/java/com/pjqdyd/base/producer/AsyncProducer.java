package com.pjqdyd.base.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**   
 * @Description:  [异步消息发送者]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class AsyncProducer {
    public static void main(String[] args) throws Exception {
        //1.创建消息生产者Producer, 并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("group1");

        producer.setNamesrvAddr("192.168.141.129:9876"); //2.指定NameServer地址 (宿主机ip:9876)

        producer.start();//3.启动Producer

        //4.创建消息对象, 指定Topic主题, Tag, 消息体
        for (int i = 0; i < 10; i++){
            Message message = new Message("base", "tag2", ("Hello World " + i).getBytes());
            //5.发送异步消息
            producer.send(message, new SendCallback() { //异步结果的回调
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("发送成功:" + sendResult);
                }
                @Override
                public void onException(Throwable throwable) {
                    System.out.println("发送异常:" + throwable);
                }
            });

            TimeUnit.SECONDS.sleep(1);
        }

        producer.shutdown(); //6.关闭生产者Producer
    }
}
