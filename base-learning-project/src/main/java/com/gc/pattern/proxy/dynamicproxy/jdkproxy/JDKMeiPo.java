package com.gc.pattern.proxy.dynamicproxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 媒婆类 进行代理
 * jdk代理必须实现接口 {@link InvocationHandler}
 * @author gaochao
 * @create 2020-09-23 22:07
 */
public class JDKMeiPo implements InvocationHandler {

  private Object target;

  public Object getProxy(Object target) {
    try {
      this.target = target;
      Class<?> cl = target.getClass();
      //生成代理对象参数解析:目标对象的类加载器;目标对象的接口,实现了InvocationHandler的类,这里为JDKMeiPo
      return  Proxy.newProxyInstance(cl.getClassLoader(),cl.getInterfaces(),this);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    before();
    //执行代理对象的方法:目标对象,方法参数
    Object invoke = method.invoke(this.target, args);
    after();
    return invoke;
  }

  public void before(){
    System.out.println("JDK媒婆,开始物色对象 before");
  }

  public void after(){
    System.out.println("JDK媒婆,结束物色对象 after");
  }


}
