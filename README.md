#### Springboot RocketMQ 案例项目

#### 项目结构:
 ```
  ├─rocketmq-demo                   rocketmq收发消息基本案例模块
  ├─springboot-rocketmq-producer    springboot整合rocketmq生产者模块
  ├─springboot-rocketmq-consumer    springboot整合rocketmq消费者模块
  ├─docker-rocketmq                 docker安装rocketmq文件
  ├─docker-rocketmq.md              docker安装rocketmq方法
  ├─.gitignore                      .gitignore文件
  ├─README.md                       README.md文件
  └─pom.xml                         父pom文件
 ```

#### RocketMQ 各个角色:

 Producer: 消息的生产者, 与NameServer的节点建立长连接
 
 Consumer: 消息的消费者, 与NameServer的节点建立长连接, 订阅Master/Slave的消息,规则由Broker配置决定
 
 Broker: 暂存和传输消息,邮局, 集群情况下可分为Master和Slave, 之间的关系通过BrokerName和BrokerId来定义
 
 NameServer: 管理Broker,各个邮局的管理机构, 集群情况下是一个无状态节点
 
 Topic: 区分消息的种类,一个发送者可以发送消息给一个或多个Topic,一个接收者可以订阅一个或多个Topic消息
 
 Message Queue: 相当于是Topic下的小分区,用于并行发送或接收消息
 
 #### 收发消息的步骤: [基本步骤](./rocketmq-demo/README.md)
