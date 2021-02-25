# 图书订阅管理系统
# 技术栈：SpringBoot+Mybatis+Redis+Rabbitmq+thymeleaf+docker

前端：使用thymeleaf模板引擎  
后端：使用springboot框架  
持久层：使用mybatis框架  
中间件：使用rabbitmq进行订单消息实时推送；使用redis进行缓存(1.使用双重检测所防止缓存穿透的问题 2.用来减少与数据库之间的I/0操作)  
# 业务流程如下：  
![](https://github.com/WYFCN/-/blob/master/Image/1.png)

![](https://github.com/WYFCN/-/blob/master/Image/2.png)
# 消息中间件说明：
使用了rabbitmq的多播模式  
用户下完订单，订单信息发送给消息队列，库存系统监听消息队列，如果有消息，取出消费，然后完成订单。然后，管理员发货，通过springboot的定时任务，定时刷新物流运输情况，等到达目的地，用户确认收货。（开多线程测试了并发情况下，尝试多人下订单时，消息队列的处理情况）  
# redis说明:
使用了RedisTemplate操作redis  
1.使用了双重检测锁防止缓存穿透的问题  
2.使用了双删延时策略解决缓存和数据库不一致的问题  
# 项目运行效果
![](https://github.com/WYFCN/-/blob/master/Image/项目照片/请勿在登陆.jpg)
![](https://github.com/WYFCN/-/blob/master/Image/项目照片/注册.jpg)
![](https://github.com/WYFCN/-/blob/master/Image/项目照片/用户主页面.jpg)
![](https://github.com/WYFCN/-/blob/master/Image/项目照片/图书详情.jpg)
![](https://github.com/WYFCN/-/blob/master/Image/项目照片/确认收货.jpg)
![](https://github.com/WYFCN/-/blob/master/Image/项目照片/admin-user.jpg)
![](https://github.com/WYFCN/-/blob/master/Image/项目照片/图书编辑.jpg)
![](https://github.com/WYFCN/-/blob/master/Image/项目照片/图书介绍.jpg)
![](https://github.com/WYFCN/-/blob/master/Image/项目照片/发送货物.jpg)
# 总结：  
    以后还会加入其他功能，继续完善，优化！
