package com.gc.pattern.proxy.dynamicproxy.cglibproxy;

import com.gc.pattern.proxy.common.Woman;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 房产中介类,帮{@link Woman} 找房子
 * @author gaochao
 * @create 2020-09-25 17:08
 */
public class HouseMiddle implements MethodInterceptor {

  /**
   * 获取代理对象:不能为静态方法 不然会报错
   * @return
   */
  public Object getProxyObject(Class<?> cl){
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(cl);
    enhancer.setCallback(this);
    return enhancer.create();
  }

  @Override
  public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
    findHouseBefore();
    methodProxy.invokeSuper(o,objects);
    findHouseAfter();
    return null;
  }

  public void findHouseBefore(){
    System.out.println("沟通需要找什么样子的房子:");
  }

  public void findHouseAfter(){
    System.out.println("给Woman找到心仪的房子了");
  }




  //测试方法
  public static void main(String[] args) {

    System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"E://cglib_proxy_class/");
    Woman proxyObject = (Woman) new HouseMiddle().getProxyObject(Woman.class);
    //当没调用的时候没有生成字节码文件
    proxyObject.findHouse();



  }
}
