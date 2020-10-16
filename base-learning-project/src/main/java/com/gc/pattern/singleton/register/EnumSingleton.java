package com.gc.pattern.singleton.register;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

/**
 * 注册式单例:
 * 这是其中的一种写法 枚举式单例模式.
 * 优点:线程安全,解析反编译发现是饿汉式的单例;无法被反射和反序列化暴力破解;实现方式优雅.枚举式单例也是《Effective Java》书中推荐的一种单例实现写法。
 * 其实还有另外一种注册式单例:容器缓存式单例 {@link ContainerSingleton}
 * @author gaochao
 * @create 2020-09-21 22:30
 */
public enum EnumSingleton {
  INSTANCE;

  private Object data;

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public static Object getInstance(){
    return INSTANCE;
  }

  public static void main(String[] args) {
    //serializableAttack();

    reflectAttack();

  }

  /**
   * 序列化暴力破坏单例
   */
  private static void serializableAttack(){
    Object s1 = null;

    Object s2 = EnumSingleton.getInstance();
    FileOutputStream fos = null;

    try {
      fos = new FileOutputStream("EnumSingleton.obj");
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(s2);
      oos.flush();
      oos.close();

      FileInputStream fis = new FileInputStream("EnumSingleton.obj");
      ObjectInputStream ois = new ObjectInputStream(fis);
      s1 =  ois.readObject();
      ois.close();
      System.out.println("s1->"+s1);
      System.out.println("s2->"+s2);
      /**
       * 这里输出的是 true,单例模式没有被破坏,为什么会出现这种情况,我们通过 jad 工具(配置教程在有道云中)来查看 EnumSingleton.java 的源码
       * 找到 EnumSingleton.class的文件,cmd进入该文件夹,输入命令 jad EnumSingleton.class 生成 EnumSingleton.jad文件,打开查看源码
       * 源码见文件 EnumSingleton.jad .我们可以发现:
       * 是饿汉式单例,在类加载好之前就在静态代码块中初始化了INSTANCE变量.那么为什么反序列化也无法破坏单例?
       * 通过阅读源码 {@link ObjectInputStream#serializableAttack()} 查看具体解析
       */
      System.out.println("s1和s2是否相等->"+(s1==s2));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 反射强制破解单例
   */
  private static void reflectAttack(){
    try {
      Class clazz = EnumSingleton.class;

      //调用newInstance方法会报错 java.lang.NoSuchMethodException 表示没有找到无参构造方法
      /*Constructor c = clazz.getDeclaredConstructor();
      c.newInstance();*/

      //通过查看 {@link Enum#Enum(String, int)} 源码,发现枚举类就只有一个构造方法,我们再次调用有参构造方法
      //也报错 java.lang.IllegalArgumentException: Cannot reflectively create enum objects
      // 所以枚举式单例无法通过反射暴力破解
      Constructor c = clazz.getDeclaredConstructor(String.class,int.class);
      c.setAccessible(true);
      Object gaochao = c.newInstance("gaochao", 666);


    }catch (Exception e){
      e.printStackTrace();
    }


  }
}
