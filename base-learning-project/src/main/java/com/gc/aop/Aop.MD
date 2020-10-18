####必须明白的几个概念:
**1. Aspect(切面):**
> 通常是一个类,里面可以定义切入点和通知.

**2.JointPoint(连接点):**
> 程序执行过程中明确的点,一般是方法的调用.

**3.Advice(通知):**
> AOP在特定的切入点上执行的增强处理,有Before,After,AfterReturning,AfterThrowing,Around

**4.PointCut(切入点):**
> 就是带有通知的连接点,在程序中主要体现为书写切入点表达式.

`AOP框架创建的对象,实际就是使用代理对目标对象功能增强.Spring中AOP代理可以使用JDK动态代理,也可以使用CGLIB代理,前者基于接口,后者基于子类`

####关于Execution表达式:
> execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern) throws-pattern?)

**参数解释:**
- modifiers-pattern：方法的操作权限 
- ret-type-pattern：返回值【必填】 
- declaring-type-pattern：方法所在的包
- name-pattern：方法名 【必填】
- param-pattern：参数名 
- throws-pattern：异常
