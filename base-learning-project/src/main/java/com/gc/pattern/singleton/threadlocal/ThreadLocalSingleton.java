package com.gc.pattern.singleton.threadlocal;

import com.gc.pattern.singleton.ConcurrentExecutor;

import java.util.concurrent.TimeUnit;

/**
 * 单例模式最后的彩蛋:线程单例 {@link ThreadLocal}
 * 不能保证其创建的对象全局唯一,但是能保证在当前单个线程里面唯一,天生线程安全.
 * 我们知道上面的单例模式为了达到线程安全的目的，给方法上锁，以时间换空间。
 * ThreadLocal将所有的对象全部放在 ThreadLocalMap中，为每个线程都提供一个对象，实际上是以空间换时间来实现线程间隔离的.
 * @author gaochao
 * @create 2020-09-22 16:44
 */
public class ThreadLocalSingleton {

  private static final ThreadLocal<ThreadLocalSingleton> LOCAL_INSTANCE =
          ThreadLocal.withInitial(()->new ThreadLocalSingleton());

  private ThreadLocalSingleton(){}

  public static ThreadLocalSingleton getInstance(){
    return LOCAL_INSTANCE.get();
  }

  public static void main(String[] args) {
    System.out.println(System.currentTimeMillis()+"::"+Thread.currentThread().getName()+"::"+ThreadLocalSingleton.getInstance());
    System.out.println(System.currentTimeMillis()+"::"+Thread.currentThread().getName()+"::"+ThreadLocalSingleton.getInstance());
    System.out.println(System.currentTimeMillis()+"::"+Thread.currentThread().getName()+"::"+ThreadLocalSingleton.getInstance());
    try {
      ConcurrentExecutor.execute(()->{
        System.out.println(System.currentTimeMillis()+"::"+Thread.currentThread().getName()+"::"+ThreadLocalSingleton.getInstance());
        try {
          TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      },20,5);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
