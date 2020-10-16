package com.gc.pattern.singleton.hungry;

/**
 * 饿汉式单例:类一加载的时候就创建一个单例，保证在调用getInstance方法之前就已经创建了一个单例
 * 饿汉式单例在类创建的同时就已经创建好一个静态的对象供系统使用，以后不再改变，所以是线程安全的，可以直接用于多线程而不会出现问题。
 * @author gaochao
 * @create 2020-06-03 11:10
 */
public class HungryStaticSingleton {

  private static final HungryStaticSingleton HUNGRY_SINGLETON ;

  //构造方法一定要私有,不私有的话就称不上是单例了
  private HungryStaticSingleton(){}

  /**
   * 写法2:静态代码块
   */
  static {
    HUNGRY_SINGLETON = new HungryStaticSingleton();
  }

}
