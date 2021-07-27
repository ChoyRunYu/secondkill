# 微服务秒杀系统
<p align="center"> 
    <img src="https://img.shields.io/badge/JDK-1.8-green.svg" alt="jdk"/>
    <img src="https://img.shields.io/badge/Spring%20Boot-2.1.3.RELEASE-blue.svg" alt="spring boot"/>
    <img src="https://img.shields.io/badge/Spring%20Cloud-Greenwich.SR5-blue.svg" alt="spring cloud"/>
    <img src="https://img.shields.io/badge/Nacos-1.4.2-blue.svg" alt="nacos" />
    <img src="https://img.shields.io/badge/Spring%20Cloud%20Alibaba-2.1.3.RELEASE-blue.svg" alt="nacos" />
    <img src="https://img.shields.io/badge/Author-Cai Runyu-pink.svg" alt="author" />
</p>

## 简介

secondkill是基于微服务技术开发的一套前后端分离秒杀系统，主要目的是为了学习秒杀业务和微服务项目。

- 前后端分离的开发模式的秒杀系统，后端使用了Spring Cloud微服务开发组件开发而成，前端使用了Vue全家桶进行开发。
- 注册中心选用了alibaba的nacos，后期准备引入nacos的配置中心。
- 提供对docker，docker-compose的支持（正在肝）

## 项目地址

- [秒杀后台管理前端](https://github.com/ChoyRunYu/secondkill-admin)
- [用户秒杀前端](https://github.com/ChoyRunYu/secondkill-vue)

## 架构图

<img src="https://choyblog.oss-cn-shenzhen.aliyuncs.com/img/%E7%B3%BB%E7%BB%9F%E6%9E%B6%E6%9E%84%E5%9B%BE.png" alt="系统架构图" style="zoom: 67%;" />

## 技术选型

| 技术                 | 版本          |
| -------------------- | ------------- |
| Spring Boot          | 2.1.3.RELEASE |
| Spring Cloud         | Greenwich.SR5 |
| Spring Cloud Alibaba | 2.1.3.RELEASE |
| Mybatis              | 3.5.6         |
| OpenFeign            | 2.1.5.RELEASE |
| Nacos                | 1.4.2         |
| Redis                | 3.2.100       |
| RabbitMQ             | 3.8.4         |
| Jedis                | 3.1.0         |
| Zuul                 | 2.1.5.RELEASE |
| Druid                | 1.1.9         |
| MySQL                | 8.0           |

## 模块介绍

| 模块名                         | 说明              |
| ------------------------------ | ----------------- |
| ├── secondkill-auth            | 鉴权服务模块      |
| ├── secondkill-common          | 公共模块          |
| ├── secondkill-rabbitmq        | rabbitmq配置模块  |
| ├── secondkill-register        | 注册中心模块      |
| ├── secondkill-service         | 微服务集合模块    |
| │     ├── secondkill-goods     | 商品服务子模块    |
| │     ├── secondkill-order     | 订单服务子模块    |
| │     └── secondkill-user      | 用户服务子模块    |
| ├── secondkill-service-api     | 微服务api集合模块 |
| │     ├── secondkill-goods-api | 商品服务api模块   |
| │     ├── secondkill-order-api | 订单服务api模块   |
| │     └── secondkill-user-api  | 用户服务api模块   |
| └── secondkill-zuul            | 网关服务模块      |

## 运行
### Docker支持
```
# 下载代码 
git clone https://github.com/ChoyRunYu/secondkill.git

# 进入目录
cd secondkill

# mvn打包
mvn clean package

# docker-compose运行
docker-compose -f docker-compose.yml up -d
```

## 截图

![image-20210727102747513](https://choyblog.oss-cn-shenzhen.aliyuncs.com/img/image-20210727102747513.png)

