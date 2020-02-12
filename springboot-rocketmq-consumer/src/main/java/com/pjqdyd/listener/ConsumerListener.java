package com.pjqdyd.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**   
 * @Description:  [消息监听器]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Component
@RocketMQMessageListener(topic = "springboot-topic", consumerGroup = "${rocketmq.consumer.group}")
public class ConsumerListener implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        System.out.println("接收到消息:" + s);
    }
}
