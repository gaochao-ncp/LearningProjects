package com.gc.pattern.proxy.dynamicproxy.jdkproxy;

import com.gc.pattern.proxy.common.Girl;
import com.gc.pattern.proxy.common.Person;

import java.lang.reflect.InvocationTargetException;

/**
 * @author gaochao
 * @create 2020-09-23 22:12
 */
public class JDKProxyTest {

  public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

    Object obj = new JDKMeiPo().getProxy(new Girl());
    System.out.println("代理对象的"+((Person)obj).getClass());

    //当Girl不实现接口的时候,无法执行,因为jdk动态代理是基于接口实现的,也就是目标类要实现接口才可以
    //Method method = obj.getClass().getMethod("findLove",null);
    //method.invoke(obj);

    ((Person) obj).findLove();

    //$Proxy0:jdk动态代理动态生成源代码->动态编译->动态加载生成的类
//    byte [] bytes = ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{Person.class});
//    FileOutputStream os = new FileOutputStream("E://$Proxy0.class");
//    os.write(bytes);
//    os.close();

  }
}
