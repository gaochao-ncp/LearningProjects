package com.gc.mybatis.reflector;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 反射工具类:用于一些基本常用的反射操作,对自定义的Person类进行操作
 * @author gaochao
 * @create 2020-08-14 17:07
 */
@Slf4j
public class ReflectorUtil {

  public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
    String clName = "com.gc.mybatis.reflector.Person";
    ReflectorUtil.printConstructor(clName);
    Constructor c = ReflectorUtil.getDeclaredConstructor(clName, String.class, String.class, String.class);
    Person p = (Person)c.newInstance("CN", "YueYang", "Victory");
    System.out.println("invoke method before p.toString() = " + p.toString());
    //获取指定的方法,并且执行方法改变值
    Class cls = getClassObject(clName);
    Method setCountry = cls.getDeclaredMethod("setCountry", String.class);

    if (Modifier.isPrivate(setCountry.getModifiers())){
      setCountry.setAccessible(Boolean.TRUE);
    }
    //执行对应的方法
    setCountry.invoke(p,"USA");
    System.out.println("invoke method after p.toString() = " + p.toString());

    //获取属性
    getField(clName,null);


  }

  /**
   * 获取Class对象
   * @param className
   * @return
   */
  public static Class<?> getClassObject(String className){
    Class<?> cl = null;
    try {
       cl = Class.forName(className);
    } catch (ClassNotFoundException e) {
      log.error("getClassObject error {}",e);
    }
    return cl;
  }

  /**
   * 获取并打印所有构造方法
   * @param className 类的全类名
   */
  public static void printConstructor(String className)  {
    Class<?> cl = getClassObject(className);

    List<String> list = new ArrayList<>();

      String key = "";
      //获取非私有的构造方法
      /*Constructor<?>[] constructors = cl.getConstructors();
      for (Constructor<?> constructor : constructors) {
        key = printConstructorDetail(constructor);
        list.add(key);
      }*/

      //获取全部的构造方法
      Constructor<?>[] declaredConstructors = cl.getDeclaredConstructors();
      for (Constructor<?> declaredConstructor : declaredConstructors) {
        key = printConstructorDetail(declaredConstructor);
        list.add(key);
      }

      if (CollUtil.isNotEmpty(list)){
        System.out.println(ArrayUtil.toString(list));
      }

  }

  /**
   * 打印构造方法详细信息
   * @param t
   */
  private static String printConstructorDetail(Constructor t){
    StringBuffer sb = new StringBuffer();
    //Modifier.toString(); //将字节码转换为对应的public private default等修饰符字符串
    switch (t.getModifiers()){
      case Modifier
              .PRIVATE:
        sb.append("private");
        break;
      case Modifier.PUBLIC:
        sb.append("public");
        break;
      default:
        sb.append("default");
    }
    sb.append(" "+t.getName()+"(");
    Type[] types = t.getGenericParameterTypes();
    if (types != null && types.length>0){
      for (int i = 0; i < types.length; i++) {
        if (i==(types.length-1)){
          //最后一位不加 ,
          sb.append(types[i].getTypeName());
        }else {
          sb.append(types[i].getTypeName()+",");
        }
      }
    }
    sb.append(")");
    return sb.toString();
  }

  /**
   * 获取指定的构造方法
   * @param className
   * @param clzs
   * @return
   */
  public static Constructor getDeclaredConstructor(String className,Class<?>... clzs){

    try {
      Class cl = getClassObject(className);
      Constructor c = cl.getDeclaredConstructor(clzs);
      if (c.getModifiers() == Modifier.PRIVATE){
        // 如果构造方法是私有的,需要开发安全权限
        c.setAccessible(Boolean.TRUE);
      }
      return c;
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }
    return null;
  }


  /**
   * 获取成员变量,指定字段名的话,获取指定;未指定的话,获取全部
   * @param clName
   * @param fieldName
   * @return
   */
  public static Set<String> getField(String clName,String fieldName){
    Set<String> set = new TreeSet<>();
    try {
    Class cl = getClassObject(clName);
    if (StrUtil.isNotBlank(fieldName)){
      //获取指定字段,并且打印字段名
      Field field = cl.getDeclaredField(fieldName);
      set.add(field.getName());
    }else {
      //获取全部字段
      Field[] fields = cl.getDeclaredFields();
      for (Field field : fields) {
        set.add(field.getName());
      }
    }
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    }
    if (CollectionUtil.isNotEmpty(set)){
      System.out.println("fields = " + ArrayUtil.toString(set));
    }
    return set;
  }

}
