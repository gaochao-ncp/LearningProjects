package com.gc.pattern.singleton.lazy;

/**
 *
 * 这是一个简单的懒汉式单例实现
 * 存在线程安全问题:在多线程的情况下,对象会被多次创建.可以通过idea 线程调试模式复现.所以我们需要改进
 * 通过关键字synchronized来实现线程安全 {@link LazySyncSingleton}
 * @author gaochao
 * @create 2020-09-21 1:06
 */
public class LazySimpleSingleton {

  private static LazySimpleSingleton INSTANCE = null;

  private LazySimpleSingleton(){}

  public static LazySimpleSingleton getInstance(){
    if (INSTANCE == null) {
      INSTANCE = new LazySimpleSingleton();
    }
    return INSTANCE;
  }


  public static void main(String[] args) {
    // 模拟线程不安全的情况,idea开启线程调试
    Thread t1 = new Thread(()->{
      System.out.println(LazySimpleSingleton.getInstance());
    });

    Thread t2 = new Thread(()->{
      System.out.println(LazySimpleSingleton.getInstance());
    });

    t1.start();
    t2.start();
  }

}
