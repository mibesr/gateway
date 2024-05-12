# gateway

### 介绍

低代码报文网关，通过简单配置就可以快速接入外部渠道。

只实现核心逻辑，需要自行移植到公司内部应用中。

### 架构设计
设计文档：

[图解报文网关：一种低代码报文网关的设计思路与核心代码实现
](https://mp.weixin.qq.com/s?__biz=MzkwOTYyODA4Nw==&mid=2247484153&idx=1&sn=e0df54003e11b38e69475d3d835de72b)

[图解渠道网关：不只是对接渠道的接口（一）](https://mp.weixin.qq.com/s?__biz=MzkwOTYyODA4Nw==&mid=2247484042&idx=1&sn=7ea7b6e736881bb8e7b41fb84b761861)

### 代码结构说明

1. api: 服务入口
2. service: 服务实现
3. engine: 处理器引擎
4. model：渠道接口配置模型
5. groovy: groovy模板处理器
6. common：公共类
7. cache：配置信息缓存
8. 测试类入口：src/test/java/com/demo/gateway/DemoTest.java

### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request

### 项目发起人信息
项目发起人：隐墨星辰

公众号/知乎/掘金：隐墨星辰

知识星球：支付翰林院
