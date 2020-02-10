
#### 基于Docker单节点安装RocketMQ

#### 这里会安装:
  >RocketMQ Server 服务
  >
  >Broker          存消息中间件
  >
  >Web Console     Web图形界面控制台

#### Linux下Docker如何安装:

 1. 修改docker-rocketmq/data/broker.conf文件中的brokerIP1=宿主机ip
 
 2. 将项目中的docker-rocketmq目录复制到Linux主机目录下,cd进入docker-rocketmq/目录下
 
 3. 运行命令`docker-compose up -d`等待镜像下载和容器启动成功
 
 4. 访问http://宿主机ip:9090进入web控制台