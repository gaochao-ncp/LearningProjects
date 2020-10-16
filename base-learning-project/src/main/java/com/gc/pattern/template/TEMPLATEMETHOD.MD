模板方法模式定义:(Template Method Pattern)
> 是指定义一个算法的骨架,并允许子类为一个或者多个步骤提供实现.
> 模板方法使得子类可以在不改变算法结构的情况下,重新定义算法的某些步骤.属于行为型设计模式

适用场景:
- 1.一次性实现算法的不变部分,并且将可变部分留给子类实现
- 2.各子类中的公共部分都被抽取到父类中,从而避免代码重复

代码实操:
- 1.课程创建流程为例：发布预习资料-->制作课件PPT-->在线直播 -->提交课堂笔记-->提交源码-->布置作业--> 检查作业。

模板方法模式和策略模式并不一样:模板方法模式可以干预流程;但是策略模式注重结果,无法干预算法内部的流程.

源码中的应用:
- AbstractList中的 abstract public E get(int index);方法
- mybatis框架中的BaseExecutor类的几个抽象方法:doUpdate,doFlushStatements,doQuery,doQueryCursor.
- GenericServlet中的service方法也是一个抽象方法.在子类HttpServlet中被实现为service和doGet,doPost方法.

