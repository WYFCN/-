图书订阅管理系统
# 技术栈：SpringBoot+Mybatis+Redis+Rabbitmq+thymeleaf+docker

前端：使用thymeleaf模板引擎  
后端：使用springboot框架  
持久层：使用mybatis框架  
中间件：使用rabbitmq进行订单消息实时推送；使用redis进行缓存(1.使用双重检测所防止缓存穿透的问题 2.用来减少与数据库之间的I/0操作)  
# 业务流程如下：  
