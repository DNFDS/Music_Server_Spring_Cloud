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
