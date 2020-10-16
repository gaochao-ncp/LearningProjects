委派模式定义:
> 基本作用就是负责任务调度和任务派遣.
> 不属于GOF23种设计模式之一
> 属于行为型模式

springmvc中 命名以*Delegate结尾,以Dispatcher*开头的都是委派模式.

spring中的应用 BeanDefinitionParserDelegate 根据不同类型委派不同的逻辑解析BeanDefinition。
