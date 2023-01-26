# SpringCloud Alibaba 介绍
- [官网](https://github.com/alibaba/spring-cloud-alibaba/blob/2022.x/README-zh.md)
  ![alt](https://uploadfiles.nowcoder.com/images/20230118/630417200_1674029088464/D2B5CA33BD970F64A6301FA75AE2EB22)


<br>
<br>


# Nacos
- [官网地址](https://github.com/alibaba/nacos)
- [文档手册](https://nacos.io/zh-cn/docs/what-is-nacos.html)
- 一个更易于构建云原生应用的动态服务发现、配置管理和服务管理平台。
- 是 Naming Configuration Service 的简写；用于注册中心 + 配置中心的整合，替代 Eureka 做服务注册中心，替代 Config 做服务配置中心；等价于 Nacos = Eureka + Config + Bus
  ![alt](https://uploadfiles.nowcoder.com/images/20230120/630417200_1674187357696/D2B5CA33BD970F64A6301FA75AE2EB22)


<br>

#### Nacos 作为服务注册中心
- [项目地址](https://github.com/HSshuo/SpringCloudAlibaba)
- 引入依赖 spring-cloud-starter-alibaba-nacos-discovery、配置 Nacos 地址等操作
- 底层集成 ribbon，所以具有远程调用的功能                      
  ![alt](https://uploadfiles.nowcoder.com/images/20230118/630417200_1674051092094/D2B5CA33BD970F64A6301FA75AE2EB22)

<br>

#### Nacos AP、CP 模式的切换
- 如果不需要存储服务级别的信息且服务实例是通过 Nacos-Client 注册，并能够保持心跳上报一般选择 AP 模型
- 如果需要在服务级别编辑或者存储配置信息，一般选择 CP，例如 K8s、DNS 服务
- 使用命令进行切换：curl-X PUT'SNACOS_SERVER:8848/nacos/vl/ns/operator/switches?entry=serverMode&value=CP'


<br>

#### Nacos 作为服务配置中心
- 引入依赖 spring-cloud-starter-alibaba-nacos-discovery、spring-cloud-starter-alibaba-nacos-config、配置 Nacos 地址等操作

<br>

#### Nacos 中的 Namespace + Group + DataID 三者关系
- namespace 是用于区分部署环境的命名空间
- group 和 DataID 逻辑上区分两个目标对象；groupid 可以放多个微服务，每个微服务对应的是 dataid

![alt](https://uploadfiles.nowcoder.com/images/20230122/630417200_1674361872726/D2B5CA33BD970F64A6301FA75AE2EB22)

- 对应在图形页面的位置                           
  ![alt](https://uploadfiles.nowcoder.com/images/20230122/630417200_1674362057131/D2B5CA33BD970F64A6301FA75AE2EB22)


<br>

**设置 DataId**
- 公式：${spring.application.name}-${spring.profiles.active}-${spring.cloud.nacos.config.file-extension}
- prefix 默认为 spring.application.name 的值，spring.profiles.active 即为当前环境对应的 profile，file-extension 为配置内容的数据格式
  ![alt](https://uploadfiles.nowcoder.com/images/20230120/630417200_1674188934661/D2B5CA33BD970F64A6301FA75AE2EB22)


<br>


#### Nacos 集群和持久化配置
- [官网配置](https://nacos.io/zh-cn/docs/deployment.html)
- [安装 mysql](https://blog.nowcoder.net/n/79e00de8ff334d448de4f63445e9a2ac)
- 默认 Nacos 使用嵌入式数据库 Derby 实现数据的存储。所以如果启动多个默认配置下的 Nacos 节点，数据存储是存在一致性问题的。为了解决这个问题，Nacos 采用了集中式存储的方式来支持集群化部署，目前只支持 MySQL 的存储
- 使用 docker 安装 Nacos 没有对应的 nacos-mysql.sql 文件，所以下载的统一版本的安装运行
  ![alt](https://uploadfiles.nowcoder.com/images/20230122/630417200_1674366898076/D2B5CA33BD970F64A6301FA75AE2EB22)
- 修改 conf 目录下的 application.propertites 文件
  ![alt](https://uploadfiles.nowcoder.com/images/20230122/630417200_1674366917506/D2B5CA33BD970F64A6301FA75AE2EB22)

<br>
<br>