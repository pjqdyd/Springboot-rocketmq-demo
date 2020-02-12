package com.pjqdyd;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**   
 * @Description:  [测试消息的生产]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProducerTest {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Test
    public void sendTest(){
        rocketMQTemplate.convertAndSend("springboot-topic", "Hello World!");
    }

}
