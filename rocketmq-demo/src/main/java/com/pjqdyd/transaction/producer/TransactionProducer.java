package com.pjqdyd.transaction.producer;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.TimeUnit;

/**   
 * @Description:  [事务消息生产者]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class TransactionProducer {

    public static void main(String[] args) throws Exception {
        //1.创建事务消息生产者
        TransactionMQProducer producer = new TransactionMQProducer("group2");
        producer.setNamesrvAddr("192.168.141.129:9876");
        //设置事务的监听器
        producer.setTransactionListener(new TransactionListener() {
            //在该方法中执行本地事务
            @Override
            public LocalTransactionState executeLocalTransaction(Message message, Object o) {
                //这里需要根据业务的执行情况, 来判断是否提交事务
                //COMMIT_MESSAGE提交事务, ROLLBACK_MESSAGE回滚事务, UNKNOW不处理就会进入回查
                return LocalTransactionState.COMMIT_MESSAGE;
            }
            //该方法是mq进行事务状态的回查
            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
                return null;
            }
        });
        producer.start();//3.启动Producer

        //4.创建消息对象, 指定Topic主题, Tag, 消息体
        for (int i = 0; i < 10; i++){
            Message message = new Message("transaction", "tag1", ("Hello World " + i).getBytes());
            //5.发送事务消息
            SendResult result = producer.sendMessageInTransaction(message, null);
            System.out.println("发送状态:" + result.getSendStatus());

            TimeUnit.SECONDS.sleep(1);
        }

        producer.shutdown(); //6.关闭生产者Producer
    }


}
