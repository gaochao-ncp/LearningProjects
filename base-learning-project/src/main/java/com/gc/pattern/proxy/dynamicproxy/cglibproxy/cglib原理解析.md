###cglib底层原理解析 [参考网址](https://blog.csdn.net/a724888/article/details/77194098?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-3.edu_weight&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-3.edu_weight)

*通过本demo中的例子来进行讲解*

####1.CglibMeipoTest将内存中的cglib生成的class文件写入磁盘,并且保存在e:盘中,可以看到生成了三个文件

>Woman$$EnhancerByCGLIB$$37c31090$$FastClassByCGLIB$$19297df1.jad
>Woman$$EnhancerByCGLIB$$37c31090.jad
>Woman$$FastClassByCGLIB$$a354017f.jad

*通过打印代理对象的class可以看到,代理对象对应的是Woman$$EnhancerByCGLIB$$37c31090.jad,我们进去查看源码(因为是通过ASM生成的字节码文件,所以设计JVM底层语言的代码),
,全部代码可以自行去阅读,这里只讲最关键的几个点:*

- 代理类实现了目标类的所有方法,但目标类中final方法,和static无法被代理
- 代理类实现的方法中每个Method都有一个MethodProxy与之对应,比如:比如Method CGLIB$findLove$0$Method 和 MethodProxy CGLIB$findLove$0$Proxy

```java
//代理方法(method.invokeSuper()调用)
final void CGLIB$findLove$0()
    {
        //调用被代理对象的findLove()方法.
        super.findLove();
    }
//被代理方法,也就是目标方法(method.invoke()会调用,这也就是为什么在拦截器中调用method.invoke会死循环,一直在调用拦截器)
public final void findLove()
{
    CGLIB$CALLBACK_0;
    if(CGLIB$CALLBACK_0 != null) goto _L2; else goto _L1
_L1:
    JVM INSTR pop ;
    CGLIB$BIND_CALLBACKS(this);
    CGLIB$CALLBACK_0;
_L2:
    JVM INSTR dup ;
    JVM INSTR ifnull 37;
       goto _L3 _L4
_L3:
    break MISSING_BLOCK_LABEL_21;
_L4:
    break MISSING_BLOCK_LABEL_37;
    this;
    CGLIB$findLove$0$Method;
    CGLIB$emptyArgs;
    CGLIB$findLove$0$Proxy;
    intercept();
    return;
    super.findLove();
    return;
}
```
>调用过程： 代理对象调用this.findLove()方法->调用拦截器->methodProxy.invokeSuper->CGLIB$findLove$0->被代理对象findLove()方法。 此时，我们发现拦截器 MethodInterceptor 中就是由MethodProxy的invokeSuper方法调用代理方法的，MethodProxy.invokeSuper()非常关键，我们分析一下它具体做了什么

```java
public class MethodProxy{
  //省略一系列代码...

  public Object invokeSuper(Object obj, Object[] args) throws Throwable {
    try {
      this.init();
      MethodProxy.FastClassInfo fci = this.fastClassInfo;
      return fci.f2.invoke(fci.i2, obj, args);
    } catch (InvocationTargetException var4) {
      throw var4.getTargetException();
    }
  }

  private static class FastClassInfo {
    FastClass f1;
    FastClass f2;
    int i1;
    int i2;

    private FastClassInfo() {
    }
  }

 //省略一系列代码...

}
```

invokerSuper()获取代理类对应的FastClass对象.并且执行invoke方法.我们再来看一下刚刚的三个文件分别是什么文件

>Woman$$EnhancerByCGLIB$$37c31090$$FastClassByCGLIB$$19297df1.jad:代理类的FastClass对象
>Woman$$EnhancerByCGLIB$$37c31090.jad:代理类
>Woman$$FastClassByCGLIB$$a354017f.jad:被代理类的FastClass对象

CGLIB动态代理只所以效率比jdk动态代理效率高的原因就是FastClass机制.为代理对象和被代理对象生成了一个FastClass类,并且分配一个index(int类型),通过这个index,FastClass可以直接定位需要调用的方法,
省去了使用反射,所以调用效率比jdk动态代理更高,但是生成代理类的时候过程更加复杂,FastClass并不是跟代理类一起生成的,只有调用MethodProxy的时候才会生成的,invoke/invokeSuper方法都调用了init()方法

MethodProxy#init():
```java
  private void init() {
    if (this.fastClassInfo == null) {
      synchronized(this.initLock) {
        if (this.fastClassInfo == null) {
          MethodProxy.CreateInfo ci = this.createInfo;
          MethodProxy.FastClassInfo fci = new MethodProxy.FastClassInfo();
          fci.f1 = helper(ci, ci.c1); //缓存中没有就新创建
          fci.f2 = helper(ci, ci.c2);
          fci.i1 = fci.f1.getIndex(this.sig1); //获取方法的下标
          fci.i2 = fci.f2.getIndex(this.sig2);
          this.fastClassInfo = fci;
          this.createInfo = null;
        }
      }
    }
  }
```



