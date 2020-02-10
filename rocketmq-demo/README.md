
#### 发送消息的步骤:

1. 创建消息生产者Producer, 并制定生产者组名

2. 指定NameServer地址

3. 启动Producer

4. 创建消息对象, 指定Topic主题,Tag和消息体

5. 发送消息

6. 关闭生产者Producer

#### 消费消息的步骤:
1. 创建消息消费者, 制定消费者组名

2. 指定NameServer地址

3. 订阅主题Topic和Tag

4. 设置回调函数处理消息

5. 启动消费者Consumer