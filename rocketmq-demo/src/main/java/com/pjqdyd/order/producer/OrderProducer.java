package com.pjqdyd.order.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**   
 * @Description:  [顺序消息生产者]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class OrderProducer {

    public static void main(String[] args) throws Exception {
        //1.创建消息生产者Producer, 并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        producer.setNamesrvAddr("192.168.141.129:9876"); //2.指定NameServer地址 (宿主机ip:9876)
        producer.start();//3.启动Producer

        for (int i = 0; i < 10; i++){
            Message message = new Message("order", "tag1", "key"+i, ("Hello World " + i).getBytes());
            /**
             * 参数1: 消息对象
             * 参数2: 消息队列的选择器
             * 参数3: 选择队列的标识 (通常是订单Id等)
             */
            SendResult result = producer.send(message, new MessageQueueSelector() {
                //list:队列集合, message消息对象, o业务标识参数
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    //用业务标识参数订单Id即参数三i与队列数取模, 得到一个固定的队列,
                    //这样每次相同的订单Id就会发往同一队列, 消费时就能保证同一订单的消息的消费顺序
                    long orderId = (long) o;
                    long index = orderId % list.size();
                    return list.get((int) index);
                }
            }, i);
            System.out.println("发送结果:" + result);
        }

        producer.shutdown();
    }

}
