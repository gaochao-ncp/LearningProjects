package com.gc.pattern.singleton.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 容器式单例.
 * 虽然缓存容器是线程安全的,但是{@link ContainerSingleton#getInstance(String)}方法并不安全,所以加上锁
 * spring中应用的例子的 {@link org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#factoryBeanInstanceCache}用的就是这种容器式单例模式
 * @author gaochao
 * @create 2020-09-22 0:20
 */
public class ContainerSingleton {

  private static final Map<String,Object> INSTANCE_MAP = new ConcurrentHashMap<>();

  private ContainerSingleton(){}

  /**
   * 通过全类名获取
   * @param className
   * @return
   */
  public static Object getInstance(String className){
    synchronized (INSTANCE_MAP){
      if (!INSTANCE_MAP.containsKey(className)){
        //不存在对象,创建后放进容器中
        Class<?> cl = null;
        try {
          cl = Class.forName(className);
          INSTANCE_MAP.put(className,cl.newInstance());
        } catch (Exception e) {
          e.printStackTrace();
          return null;
        }
      }
      return INSTANCE_MAP.get(className);
    }

  }

}
