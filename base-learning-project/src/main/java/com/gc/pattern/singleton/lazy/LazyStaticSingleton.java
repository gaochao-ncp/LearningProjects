package com.gc.pattern.singleton.lazy;

import com.gc.pattern.singleton.hungry.SerializableSingleton;

import java.lang.reflect.Constructor;

/**
 * 静态内部类单例模式
 * 利用类初始化的机制,静态内部类先于外部类加载完毕.这种方法既能保证效率,又能兼顾不造成内存浪费
 * 以上的单例模式,构造方法都只是用了{@code private}修饰符,并没有做任何处理.这里存在一个问题,那就是会被反射进行暴力破解.
 * 本类中的{@link LazyStaticSingleton#reflectorAttack()}是通过反射暴力破解单例模式的过程,因为会调用无参构造方法,所以我们需要在私有构造方法中进行处理,加上如下代码:
 * <p>
 *    if (LazyInnerClass.INSTANCE != null){
 *       throw new RuntimeException("请勿暴力破解单例:目前已经存在实例:"+LazyInnerClass.INSTANCE);
 *     }
 * </p>
 * 至此,最牛逼的单例模式写出来了.
 * 可以有效的防止通过反射的暴力破解,但是随之问题来了,还可以通过序列化来进行破解单例 {@link SerializableSingleton}
 * @author gaochao
 * @create 2020-09-21 14:17
 */
public class LazyStaticSingleton {

  private LazyStaticSingleton(){
    if (LazyInnerClass.INSTANCE != null){
      throw new RuntimeException("请勿暴力破解单例:目前已经存在实例:"+LazyInnerClass.INSTANCE);
    }
  }

  public static LazyStaticSingleton getInstance(){
    return LazyInnerClass.INSTANCE;
  }

  private static class LazyInnerClass{
    private static final LazyStaticSingleton INSTANCE = new LazyStaticSingleton();
  }

  /**
   * 利用反射暴力破解单例模式,最后返回的结果是 false
   */
  private static void reflectorAttack(){
    try {
      // 获取类
      Class cl = LazyStaticSingleton.class;
      // 获取无参构造方法
      Constructor defaultConstructor = cl.getDeclaredConstructor();
      // 设置权限,强吻(强制访问)
      defaultConstructor.setAccessible(true);
      // 反射暴力破解
      Object o = defaultConstructor.newInstance();
      Object o1 = LazyStaticSingleton.getInstance();
      System.out.println(o == o1);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] rgs) {
    //利用线程模拟器模拟高并发的情况
//    try {
//      ConcurrentExecutor.execute(()->{
//        System.out.println(LazyStaticSingleton.getInstance());
//      }, 10, 6);
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
    reflectorAttack();
  }

}
