package com.gc.pattern.singleton.lazy;

/**
 * 双重检查锁的单例模式
 * 相对于 {@link LazySyncSingleton} 而言,性能有了提升并且线程安全,但是还是用到了 synchronized 关键字加锁,总归要上锁.对性能有一定影响
 * 我们从类初始化的角度来考虑实现一种更为高效的单例模式 {@link LazyStaticSingleton}
 * @author gaochao
 * @create 2020-09-21 1:23
 */
public class LazyDoubleCheckSingleton {

  private static LazyDoubleCheckSingleton INSTANCE ;

  private LazyDoubleCheckSingleton(){}

  public static LazyDoubleCheckSingleton getInstance(){
    if (INSTANCE == null){
      //锁住这个对象和锁住这个类效果一样的
      //但是这里不能使用synchronized (INSTANCE)锁住对象,因为对象还没初始化的时候是null,会出现空指针异常
      synchronized (LazyDoubleCheckSingleton.class){
        //为什么要加上双重检查,为了防止创建两次对象
        if (INSTANCE == null){
          INSTANCE = new LazyDoubleCheckSingleton();
        }
      }
    }
    return INSTANCE;
  }

  public static void main(String[] args) {
    // 模拟线程不安全的情况,idea开启线程调试
    Thread t1 = new Thread(()->{
      System.out.println(LazyDoubleCheckSingleton.getInstance());
    });

    Thread t2 = new Thread(()->{
      System.out.println(LazyDoubleCheckSingleton.getInstance());
    });

    t1.start();
    t2.start();
  }

}
