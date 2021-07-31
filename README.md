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

- 前后端分离开发模式的秒杀系统，后端使用了Spring Cloud微服务组件开发而成，前端使用了Vue全家桶进行开发。
- 注册中心选用了alibaba的nacos，后期准备引入nacos的配置中心。
- 提供对docker，docker-compose的支持
- 图片上传支持阿里云oss存储

## 项目链接

| 名称         | github                                        | gitee                                        |
| ------------ | --------------------------------------------- | -------------------------------------------- |
| 后台管理前端 | https://github.com/ChoyRunYu/secondkill-admin | https://gitee.com/Choyrunyu/secondkill-admin |
| 秒杀前端     | https://github.com/ChoyRunYu/secondkill-vue   | https://gitee.com/Choyrunyu/secondkill-vue   |

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

| 模块名                         | 说明（端口）           |
| ------------------------------ | ---------------------- |
| ├── secondkill-auth            | 鉴权服务模块（8002）   |
| ├── secondkill-common          | 公共模块               |
| ├── secondkill-rabbitmq        | rabbitmq配置模块       |
| ├── secondkill-register        | 注册中心模块（8848）   |
| ├── secondkill-service         | 微服务集合模块         |
| │     ├── secondkill-goods     | 商品服务子模块（8021） |
| │     ├── secondkill-order     | 订单服务子模块（8010） |
| │     └── secondkill-user      | 用户服务子模块（8001） |
| ├── secondkill-service-api     | 微服务api集合模块      |
| │     ├── secondkill-goods-api | 商品服务api模块        |
| │     ├── secondkill-order-api | 订单服务api模块        |
| │     └── secondkill-user-api  | 用户服务api模块        |
| └── secondkill-zuul            | 网关服务模块（8000）   |

## 快速开始
### 本地开发

需要往hosts中添加以下域名，本地运行需要有rabbitmq和redis环境，需要跑5个微服务，1个注册中心和其他中间件，建议不低于16g内存。

**注意**：需要配置公共模块中oss.properties中的oss信息，才能进行商品图片的上传

```
127.0.0.1   secondkill-register
127.0.0.1   secondkill-mysql
127.0.0.1   secondkill-redis
127.0.0.1   secondkill-rabbitmq
127.0.0.1   secondkill-auth
127.0.0.1   secondkill-zuul
127.0.0.1   secondkill-goods
127.0.0.1   secondkill-order
127.0.0.1   secondkill-user
```
### Docker支持
```
## 秒杀前端
# 克隆代码
git clone https://github.com/ChoyRunYu/secondkill-admin
 
# 进入目录、安装依赖、构建
cd secondkill-admin && npm install && npm run build:docker

# docker启动
cd docker && docker-compost up -d

## 后台管理
# 克隆项目
git clone https://github.com/ChoyRunYu/secondkill-vue.git

# 安装依赖、打包构建
cd secondkill-vue && npm install && npm run build:docker

# 运行docker-compose
cd docker && docker-compose up -d


## 微服务
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

#### 注册中心

![image-20210729223115991](https://choyblog.oss-cn-shenzhen.aliyuncs.com/img/image-20210729223115991.png)

#### docker的portainer管理面板

![image-20210731145510301](https://choyblog.oss-cn-shenzhen.aliyuncs.com/img/image-20210731145510301.png)

#### 秒杀界面

![image-20210725141627940](https://choyblog.oss-cn-shenzhen.aliyuncs.com/img/image-20210725141627940.png)

![image-20210725141645800](https://choyblog.oss-cn-shenzhen.aliyuncs.com/img/image-20210725141645800.png)

![image-20210725141704024](https://choyblog.oss-cn-shenzhen.aliyuncs.com/img/image-20210725141704024.png)

![image-20210725141718800](https://choyblog.oss-cn-shenzhen.aliyuncs.com/img/image-20210725141718800.png)

#### 后台管理



![image-20210725141527793](https://choyblog.oss-cn-shenzhen.aliyuncs.com/img/image-20210725141527793.png)

![image-20210725141558355](https://choyblog.oss-cn-shenzhen.aliyuncs.com/img/image-20210725141558355.png)
