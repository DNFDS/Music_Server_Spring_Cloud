# Twinkle_Music_Sever_SpirngCloud
重构Andriod/Vue 音乐后端 SpringCloud版本

## 软件系统架构:
	Andriod/Vue+Spring Cloud+Mybatis+Oracle+Redis
	安卓端地址：https://github.com/DNFDS/Twinkle
	Web前端地址：https://github.com/lemon1040/2019-music-front

## 微服务架构:
    Eruka-server          微服务注册中心
    Zuul-gateway          实现网关以及拦截器实现白名单
    Standard-consumer     实现用户主业务逻辑处理
    Admin-consumer        实现管理员业务逻辑处理
    Cms-consumer          通过Redis集群实现单曲、歌手、歌单、专辑排行榜缓存
    Feign-api             微服务调用接口
    UserLog		      通过自定义注解使用AOP生成数据库日志
    
## 最终一致性分布式事务
### 解决方案：rabbitmq可靠消息最终一致性
### 案例：用户购买音乐
### 设计：
#### 上游服务：支付系统
* 1.开启事务
* 2.调用isSongBought feign-api判断歌曲是否已购买 若是则return failed
* 3.redis加锁 锁名 key:buySong+userid+songid value: sessionid 加锁失败则表示有进程在执行该事务 return failed
* 4.将消息插入本地消息表  messagename:buySong+userid+songid state:new  表示新建消息 若失败则return failed
* 5.将消息发送至远程消息中心 若失败则抛runtimeexception 回滚事务
* * 6.执行扣费服务 若失败则抛runtimeexception 回滚事务
* 7.修改本地消息表状态为finished 若失败则抛runtimeexception 回滚事务
* 8.通知远程消息中心更新状态为ready
* 9.redis解锁
* 10.return success
#### 消息中心
* 1.新建消息到redis中
* 2.更改消息状态为ready
* 3.将消息发送至rabbitmq 同时将消息状态改变为wait_publish
* 4.收到rabbitmq ack返回 否则重发 将消息状态改变为published
* 6.轮询消息表 若有状态为new的消息且超时则调用上游服务接口查询消息表若为finished则将本地消息状态改为ready并发送到消息队列若不存在则删除该消息
* 7.轮询消息表 若有状态为published且超时则调用下游服务接口查询消息表若为finished则将本地消息状态改为consumed若不存在则重新发送该消息
#### 下游服务：用户主系统
* 1.开启事务
* 2.新建消息到本地消息表 messagename:buySong+userid+songid state:recevied 若已存在说明消息重复 丢弃该消息 若失败则return failed
* 3.执行将歌曲添加至已购买表服务 若失败则抛runtimeexception 回滚事务
* 4.修改本地消息变量表状态为finished 若失败则抛runtimeexception 回滚事务
* 5.用rabbitmq异步通知远程消息中心更新状态为consumed 
* 6.return success


