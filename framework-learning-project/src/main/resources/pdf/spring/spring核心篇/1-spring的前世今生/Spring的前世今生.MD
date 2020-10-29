### Spring模式的前世今生
> Java Bean -> EJB -> POJO  

`JavaBean是从Java Applet发展演变而来,随着业务复杂,EJB应运而生,但是因为其笨重并且复杂,被Spring的POJO所替代`


### Spring的设计初衷
- 1、基于 POJO 的轻量级和最小侵入性编程； 
- 2、通过依赖注入和面向接口松耦合； 
- 3、基于切面和惯性进行声明式编程； 
- 4、通过切面和模板减少样板式代码；

主要是通过面向Bean编程(BOP),依赖注入(DI),面向切面编程(AOP)实现的.

### Spring的模块结构
**Spring之核心模块:**  

|模块名称|主要功能|
|----|----|
|spring-core|控制反转（Inversion of Control, IOC）和依赖注入（Dependency Injection, DI）最基本的实现。|
|spring-beans|BeanFactory和Bean的装配.BeanFactory 接口是Spring框架中的核心接口，它是工厂模式的具体实现。BeanFactory使用控制反转对应用程序的配置和依赖性规范与实际的应用程序代码进行了分离。但BeanFactory容器实例化后并不会自动实例化Bean，只有当Bean被使用时BeanFactory容器才会对该Bean进行实例化与依赖关系的装配。|
|spring-context|定义Spring的上下文Context,即IOC容器。ApplicationContext是该模块的核心接口，它的超类是BeanFactory。与BeanFactory不同，ApplicationContext容器实例化后会自动对所有的单实例Bean 进行实例化与依赖关系的装配，使之处于待用状态。|
|spring-context-support|对Spring IOC容器的扩展支持，以及IOC子容器。|
|spring-context-indexer|Spring的类管理组件和Classpath扫描。|
|spring-expression|统一表达式语言（EL）的扩展模块。|

**Spring之AOP和设备支持:**

|模块名称|主要功能|
|----|----|
|spring-aop|面向切面编程的应用模块,整合Asm,JDKProxy,CGLib|
|spring-aspects|集成AspectJ,AOP应用框架|
|spring-instrument|动态Class Loading模块|

**Spring之数据集成和访问:**

|模块名称|主要功能|
|----|----|
|spring-jdbc|Spring提供的JDBC抽象框架的主要实现模块，用于简化Spring JDBC操作 。|
|spring-tx|Spring JDBC事务控制实现模块|
|spring-orm|主要集成 Hibernate, Java Persistence API (JPA) 和 Java Data Objects (JDO)|
|spring-jms|Java Messaging Service能够发送和接收信息|
|spring-oxm|将Java对象映射成XML数据，或者将XML数据映射成Java对象|

**Spring之Web组件:**

|模块名称|主要功能|
|----|----|
|spring-web|提供了最基础Web支持，主要建立于核心容器之上，通过Servlet或者Listeners来初始化IOC容器。也包含一些与Web相关的支持|
|spring-webmvc|实现了Spring MVC（model-view-Controller）的Web应用。|
|spring-websocket|主要是与Web前端的全双工通讯的协议。|
|spring-webflux|一个新的非堵塞函数式Reactive Web框架，可以用来建立异步的，非阻塞，事件驱动的服务。|

**Spring之通信报文:**  
|模块名称|主要功能|
|----|----|
|spring-messaging|从Spring4开始新加入的一个模块，主要职责是为Spring框架集成一些基础的报文传送应用|

**Spring之集成测试:**

|模块名称|主要功能|
|----|----|
|spring-test|主要为测试提供支持|

**Spring之集成兼容:**  

|模块名称|主要功能|
|----|----|
|spring-framework-bom|Bill of Materials.解决Spring的不同模块依赖版本不同问题。|

### IDEA:利用Gradle构建源码
参考有道云笔记.

1.首先下载源码:[spring源码下载地址](https://github.com/spring-projects/spring-framework/archive/v5.0.2.RELEASE.zip) ;[Gradle 4.0 下载地址](https://gradle.org/next-steps/?version=4.0&format=bin)

2.下载好之后解压spring源码和gradle-4.0的压缩包.然后进行Gradle环境变量的配置:  
`GRADLE_HOME=解压的gradle包的目录`  
`Path:%GRADLE_HOME%\bin`

3.导入IDEA。打开 IntelliJ IDEA，点击 Close Project->Open or Import->选择解压的spring的文件夹

4.等待构建完成，在网络良好的情况下大约需要 10 分钟便可自动构建完成在.IDEA 中，如果Project下的子项目文件夹变成粗体字之后，说明已经构建成功。  
还有一种验证方式是：找到ApplicationContext类，按 Ctrl + Shift + Alt + U，出现类图界面说明构建成功。

