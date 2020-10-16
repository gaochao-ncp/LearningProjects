package com.gc.pattern.singleton.hungry;

import java.io.*;

/**
 * 反序列化暴力破解单例以及怎么解决
 * @author gaochao
 * @create 2020-09-21 15:07
 */
public class SerializableSingleton implements Serializable {

  private static final SerializableSingleton INSTANCE = new SerializableSingleton();

  private SerializableSingleton(){}

  public static SerializableSingleton getInstance(){
    return INSTANCE;
  }

  /**
   * 防止序列化破坏单例.为什么?
   * {@link ObjectInputStream#readOrdinaryObject(boolean)} 方法中会执行对象的 readResolve() 方法并返回.所以重写这个方法可以防止序列化破坏单例
   * 通过查看源码可以看到,实际上实例化了两次,只不过新创建的对象没有被返回,返回的是readResolve()方法中的对象,因此,创建对象的频率增大,意味着内存开销也变大了.下面我们马上进入另外一个单例模式
   * 注册式单例 {@link com.gc.pattern.singleton.register.EnumSingleton}
   * @return
   */
  public SerializableSingleton readResolve(){
    return INSTANCE;
  }

  //序列化破解单例
  public static void main(String[] args) {

    SerializableSingleton s1 = null;
    SerializableSingleton s2 = SerializableSingleton.getInstance();
    FileOutputStream fos = null;

    try {
      fos = new FileOutputStream("SerializableSingleton.obj");
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(s2);
      oos.flush();
      oos.close();

      FileInputStream fis = new FileInputStream("SerializableSingleton.obj");
      ObjectInputStream ois = new ObjectInputStream(fis);
      s1 = (SerializableSingleton) ois.readObject();
      ois.close();
      System.out.println("s1->"+s1);
      System.out.println("s2->"+s2);
      /**
       * 这里输出的是 false,可见单例模式被破坏了,那么我们怎么来防止这种情况发生?
       * 只需要在单例类中重写加入 readResolve() 方法,在这个方法中返回单例即可{@link SerializableSingleton#readResolve()} .为什么?
       */
      System.out.println("s1和s2是否相等->"+(s1==s2));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
