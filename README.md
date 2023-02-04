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



# Sentinel
- [官网](https://github.com/alibaba/Sentinel)
- 轻量级的流量控制、熔断降级 Java 库
- Sentinel 是面向分布式、多语言异构化服务架构的流量治理组件，主要以流量为切入点，从流量路由、流量控制、流量整形、熔断降级、系统自适应过载保护、热点流量防护等多个维度来帮助开发者保障微服务的稳定性。
- Sentinel 分为两部分：核心库（Java 客户端）不依赖任何框架/库，能够运行在 Java8 以上运行时环境，同时对 Dubbo、SpringCloud 框架也有较好的支持；控制台（DashBoard）主要负责管理推送规则、监控、管理机器信息等

<br>

#### 下载
- 注意 docker 安装不行，一直空白界面，采用多种方式例如添加配置 client-ip 仍不行，错误一直超时。可能的原因是 sentinel和部署的微服务一定要在同一个内网中
- [docker 安装参考](https://cloud.tencent.com/developer/article/2099895)
- 最后使用本地运行 sentinel 解决，下载地址 [sentinel:1.8.6](https://github.com/alibaba/Sentinel/releases/tag/1.8.6)
- 启动命令：java -jar sentinel-dashboard-1.8.6.jar --server.port=8081
  ![alt](https://uploadfiles.nowcoder.com/images/20230131/630417200_1675164435619/D2B5CA33BD970F64A6301FA75AE2EB22)


<br>

#### 主要特性
![alt](https://uploadfiles.nowcoder.com/images/20230126/630417200_1674718276718/D2B5CA33BD970F64A6301FA75AE2EB22)

<br>

#### 基本概念
![alt](https://uploadfiles.nowcoder.com/images/20230126/630417200_1674719839247/D2B5CA33BD970F64A6301FA75AE2EB22)


<br>

#### 熔断框架比较
![alt](https://github.com/HSshuo/PictureBed/blob/main/springcloudAlibaba/sentinel/%E6%A1%86%E6%9E%B6%E6%AF%94%E8%BE%83.png?raw=true)


<br>
<br>

## 服务限流
- Sentinel 的设计理念是让您自由选择控制的角度，并进行灵活组合，从而达到想要的效果
- 流量控制有以下几个角度:
  1. 资源的调用关系，例如资源的调用链路，资源和资源之间的关系
  2. 运行指标，例如 QPS、线程池、系统负载等
  3. 控制的效果，例如直接限流、冷启动、排队等

<br>

#### 流控规则
- 资源名：唯一名称，默认请求路径
- 针对来源：Sentinel 可以针对调用者进行限流，填写微服务名，默认 default（不区分来源）
- 阈值类型/单机阈值
  1. QPS（每秒的请求数量）：当调用该 api 的 QPS 达到阈值的时候，进行限流
  2. 线程数：当调用该 api 的线程数达到阈值的时候，进行限流

![alt](https://uploadfiles.nowcoder.com/images/20230201/630417200_1675220254201/D2B5CA33BD970F64A6301FA75AE2EB22)

<br>

#### 流控模式
- 直接：api 达到限流条件时，直接限流
- 关联：当关联的资源达到阈值时，就限流自己；可以通过 JMeter(并发)、PostMan(串行) 测试
- 链路：只记录指定链路上的流量，指定资源从入口资源进来的流量，如果达到阈值，就进行限流（api 级别的针对来源）

<br>

#### 流控效果
- 快速失败：直接失败，抛出异常

![alt](https://uploadfiles.nowcoder.com/images/20230201/630417200_1675233468083/D2B5CA33BD970F64A6301FA75AE2EB22)

- 预热(Warm Up)：**根据 codeFactor（冷加载因子，默认3）的值，系统最开始的初始阈值为 阈值/codeFactor ，然后经过预热时长才慢慢达到设置的 QPS 阈值**。应用于秒杀系统在开启的瞬间，会有很多流量上来，很有可能把系统打死，预热方式就是把为了保护系统，可慢慢的把流量放进来，慢慢的把阀值增长到设置的阀值

- 排队等待：匀速排队，让请求以匀速的速度通过，阈值类型必须设置 QPS，否则无效；对应的算法是漏桶算法；主要用于处理间隔性突发的流量，例如消息队列。在某一秒有大量请求到来，而接下来的几秒则处于空闲状态，希望系统能过在接下来的空闲期间逐渐处理这些请求，而不是在第一秒直接拒绝多余的请求

<br>

#### 限流提示
- sentinel系统默认的提示：Blocked by Sentinel (flow limiting)
- 自定义提示：通过注解 @SentinelResource(value = "testHotKey", blockHandler = "dealHandler_testHotKey")，处理的是 sentinel 控制台配置的违规情况，有 blockHandler 方法兜底处理，**不会管运行时异常**；fallback 属性管理运行时异常

<br>
<br>

## 降级规则
- RT（平均响应时间，秒级）
  1. 平均响应时间超出阈值 且 在 1s 内通过的请求 >= 5，两个条件同时满足后触发降级
  2. 当资源被降级后，在接下来的降级时间窗口之内，对该资源的调用都自动熔断
  3. RT 最大为4900，仍需更大需要修改 -Dcsp.sentinel.statistic.max.rt=XXX 生效
- 异常比（秒级）：QPS（资源每秒请求量） >= 5 且 异常比例（每秒的）超过阈值时，触发降级；当资源被降级后，在接下来的降级时间窗口之内，对该资源的调用都自动熔断；在时间窗口期结束后，关闭降级
- 异常数（分钟级）：异常数（每分钟）超过阈值时，触发降级；时间窗口期结束后，关闭降级

<br>
<br>

## 热点规则
- 热点参数限流会统计传入参数中的热点参数，并根据配置的限流阈值与模式，对包含热点参数的资源调用进行限流。热点参数限流可以看做是一种特殊的流量控制，仅对包含热点参数的资源调用生效
- 例如：方法里面的第一个参数（指的是后台方法里的第一个参数而不是前端传入的第一个参数）只要 QPS 超过每秒1次，马上进行降级处理

``` java
/**
     * sentinel系统默认的提示：Blocked by Sentinel (flow limiting)
     * 自定义限流异常，testHotKey为资源名；blockHandler为fallback方法
     *
     * @SentinelResource 处理的是 sentinel 控制台配置的违规情况，有 blockHandler 方法配置的兜底处理
     * 对于 RuntimeException 异常，例如 int age = 10 / 0，会走异常，@SentinelResource 注解不管
     * @param p1
     * @param p2
     * @return
     */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "dealHandler_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1, @RequestParam(value = "p2",required = false) String p2){
        return "------testHotKey";
    }

    public String dealHandler_testHotKey(String p1, String p2, BlockException exception) {
        return "-----dealHandler_testHotKey";
    }
```
- 参数例外项：某些时候我们期望参数是某个特殊值的时候，它的限流值和平时不一样

![alt](https://github.com/HSshuo/PictureBed/blob/main/springcloudAlibaba/sentinel/%E7%83%AD%E7%82%B9%E8%A7%84%E5%88%99.png?raw=true)



<br>
<br>

## 系统规则
- [官网](https://github.com/alibaba/Sentinel/wiki/%E7%B3%BB%E7%BB%9F%E8%87%AA%E9%80%82%E5%BA%94%E9%99%90%E6%B5%81)
- 系统保护规则是从应用级别的入口流量进行控制，从单台机器的 load、CPU 使用率、平均 RT、入口 QPS 和 并发线程数等几个维度监控应用指标，让系统尽可能跑在最大吞吐量的同时保证系统整体的稳定性
- 系统保护规则是应用整体维度的，而不是资源维度的，并且仅对入口流量生效。入口流量指的是进入应用的流量，比如 web 服务或者 Dubbo 服务端接收的请求，都属于入口流量

<br>

#### 支持模式
- Load 自适应(仅对 linux/Unix-like 机器生效)：系统的 load1 作为启发指标，进行自适应系统保护。当系统 load1 超过设定的启发值，且系统当前的并发线程数超过估算的系统容量时才会触发系统保护（BBR阶段）。系统容量由系统的 maxQPS * minRT 估算得出。设定参考值一般是 CPU cores * 2.5
- CPU usage（1.5.0+版本）：当系统 CPU 使用率超过阈值即触发系统保护（取值范围 0.0-1.0），比较灵敏
- 平均 RT：当单台机器上所有入口流量的平均 RT达到阈值即触发系统保护，单位是毫秒
- 并发线程数：当单台机器上所有入口流量的并发线程数达到阈值即触发系统保护
- 入口 QPS：当单台机器上所有入口流量的 QPS 达到阈值即触发系统保护


<br>
<br>

## @SentinelResource 注解
- blockHandler 管理配置违规情况
- fallback 管理运行时异常，也就是业务代码异常
- 同时配置同时出问题，blockHandler > fallback

``` java
/**
     * id = 4, 抛出运行时异常，会走 fallback 配置的方法，只负责业务异常
     * blockHandler 负责在 sentinel 里面配置的降级限流
     * 同时配置，blockHandler > fallback
     * @param id
     * @return
     */
    @RequestMapping("/consumer/fallback/{id}")
    @SentinelResource(value = "fallback", blockHandler = "blockHandler", fallback = "blockFallBack")
    public CommonResult<Payment> fallback(@PathVariable Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);

        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常....");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录,空指针异常");
        }

        return result;
    }

    /**
     * 业务类异常
     * @param id
     * @param throwable
     * @return
     */
    public CommonResult blockFallBack(@PathVariable Long id, Throwable throwable) {
        return new CommonResult(4444, "运行时异常，fallback跳转，blockException: " + throwable.getMessage(), new Payment(id, "null"));
    }


    /**
     * sentinel控制台配置异常
     * @param id
     * @param blockException
     * @return
     */
    public CommonResult blockHandler(@PathVariable Long id, BlockException blockException) {
        return new CommonResult<>(4445, "sentinel里面配置异常，blockHandler-sentinel限流, 无此流水: blockException  " + blockException.getMessage(), new Payment(id, "null"));
    }
```
<br>


#### 按照资源名称限流
- @SentinelResource(value = "byResource", blockHandler = "handleException")，其中 value 属性对应的就是资源名
- 没有下划线 /

![alt](https://github.com/HSshuo/PictureBed/blob/main/springcloudAlibaba/sentinel/%E6%8C%89%E7%85%A7%E8%B5%84%E6%BA%90%E5%90%8D%E9%85%8D%E7%BD%AE%E9%99%90%E6%B5%81.png?raw=true)

<br>

#### 按照 URL 地址限流
- @GetMapping 对应URL，有下划线 /

![alt](https://github.com/HSshuo/PictureBed/blob/main/springcloudAlibaba/sentinel/URL%E9%85%8D%E7%BD%AE%E9%99%90%E6%B5%81.png?raw=true)

<br>

#### 客户自定义限流处理逻辑
- sentinel 控制台需要通过资源名配置，URL配置不起作用
- @SentinelResource(value = "customerBlockHandler", blockHandlerClass = CustomerBlockHandler.class, blockHandler = "handleException2")；blockHandlerClass 对应的是类里面的具体方法 handleException2

``` java
/**
     * 自定义通用的限流处理逻辑，sentinel 控制台需要通过资源名配置，URL配置不起作用
     * blockHandlerClass = CustomerBlockHandler.class
     * blockHandler = handleException2
     * 上述配置：找CustomerBlockHandler类里的handleException2方法进行兜底处理
     */
    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class, blockHandler = "handleException2")
    public CommonResult customerBlockHandler() {
        return new CommonResult(200,"按客户自定义限流处理逻辑");
    }


/**
 * @author SHshuo
 * @data 2023/2/3--10:32
 * 自定义通用的限流处理逻辑
 */
public class CustomerBlockHandler {

    public static CommonResult handleException2(BlockException exception){
        return new CommonResult(2022,"自定义的限流处理信息......handleException2");
    }

    public static CommonResult handleException(BlockException exception){
        return new CommonResult(2022,"自定义的限流处理信息......handleException");
    }
}
```


<br>
<br>

## 持久化规则
- 一旦我们重启应用，sentinel 规则将会消失，所以需要将配置规则进行持久化配置
- 将限流配置规则持久化进 nacos 保存，需要在 nacos 中手写 JSON 配置，之后刷新 sentinel 控制台即出现配置规则


<br>
<br>

## 总结
- [项目地址](https://github.com/HSshuo/SpringCloudAlibaba)
- 感觉主要针对 sentinel 控制台进行操作，Hystrix 主要通过代码来实现

<br>
<br>



