package com.gc.pattern.singleton.lazy;

/**
 *
 * 懒汉式单例，特点是类加载的时候没有生成单例，只有当调用的时候才生成实例
 * 注意：如果编写的是多线程程序，则不要删除上例代码中的关键字 volatile 和 synchronized，否则将存在线程非安全的问题。
 * 如果不删除这两个关键字就能保证线程安全，但是每次访问时都要同步，会影响性能，且消耗更多的资源，这是懒汉式单例的缺点。
 * @author gaochao
 * @create 2020-06-03 10:46
 */
public class LazySingleton {

  // volatile
  private static  LazySingleton LAZY_SINGLETON = null;

  private LazySingleton(){}//构造方法声明成私有，只能在本类内部调用，防止外部实例化

  public static synchronized LazySingleton getInstance(){
    if (LAZY_SINGLETON == null) {
      LAZY_SINGLETON = new LazySingleton();
    }
    return LAZY_SINGLETON;
  }

}
