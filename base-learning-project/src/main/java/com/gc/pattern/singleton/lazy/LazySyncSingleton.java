package com.gc.pattern.singleton.lazy;

/**
 * 通过关键字 LazySyncSingleton 来实现线程安全.
 * 当有线程线程抢占到 getInstance()方法的时候,其他线程会变成 MONITOR 阻塞状态,无法进入该方法,也就实现了线程安全
 * 缺点:synchronized是重锁,会锁住整个类.当线程增多,大量线程阻塞,CPU压力会增大,导致执行效率大大降低了.因此这个实现方法不可以取
 * 所以我们继续优化,增加锁的粒度 {@link LazyDoubleCheckSingleton}
 * @author gaochao
 * @create 2020-09-21 1:15
 */
public class LazySyncSingleton {

  private static LazySyncSingleton INSTANCE = null;

  private LazySyncSingleton(){}

  public synchronized static LazySyncSingleton getInstance(){
    if (INSTANCE == null) {
      INSTANCE = new LazySyncSingleton();
    }
    return INSTANCE;
  }


  public static void main(String[] args) {
    Thread t1 = new Thread(()->{
      System.out.println(LazySyncSingleton.getInstance());
    });

    Thread t2 = new Thread(()->{
      System.out.println(LazySyncSingleton.getInstance());
    });

    t1.start();
    t2.start();
  }
}
