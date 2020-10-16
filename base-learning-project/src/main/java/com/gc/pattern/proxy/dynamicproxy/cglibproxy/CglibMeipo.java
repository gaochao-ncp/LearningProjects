package com.gc.pattern.proxy.dynamicproxy.cglibproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib动态代理
 * 需要实现 {@link MethodInterceptor} 接口,并实现其中的方法.
 * 通过集成该类生成FastClass文件进行代理,注意有个坑,那就是final和static方法无法代理
 * @author gaochao
 * @create 2020-09-25 11:42
 */
public class CglibMeipo implements MethodInterceptor {

  public Object getInstance(Class<?> cl){
    Enhancer enhancer = new Enhancer();
    //设置需要代理对象的class类型
    enhancer.setSuperclass(cl);
    //设置回调
    enhancer.setCallback(this);
    return enhancer.create();
  }


  @Override
  public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
    //增强
    before();
    // 调用代理对象生成的代理方法
    Object result = methodProxy.invokeSuper(o, objects);
    after();
    return result;
  }

  public void before(){
    System.out.println("cglib 媒婆开始帮忙做事之前的准备动作");
  }

  public void after(){
    System.out.println("cglib 媒婆帮忙做完事最后的结束动作");
  }
}
