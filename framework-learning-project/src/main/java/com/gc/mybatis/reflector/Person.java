package com.gc.mybatis.reflector;

import lombok.Data;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * 反射测试的类
 * @author gaochao
 * @create 2020-08-14 17:08
 */
@Data
public class Person {

  public String country;
  public String city;
  private String name;
  private String province;
  private Integer height;
  private Integer age;

  public Person(){
    System.out.println("调用Person无参构造方法");
  }

  //私有有参构造
  private Person(String country, String city, String name) {
    this.country = country;
    this.city = city;
    this.name = name;
  }

  //公共有参构造
  public Person(String country, Integer age) {
    this.country = country;
    this.age = age;
  }

  public void getGenericHelper(HashMap<String, Integer> hashMap) {
    System.out.println("进入getGenericHelper方法");
  }

  public Class getGenericType() {
    try {
      HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
      Method method = getClass().getDeclaredMethod("getGenericHelper",HashMap.class);
      Type[] genericParameterTypes = method.getGenericParameterTypes();
      if (null == genericParameterTypes || genericParameterTypes.length < 1) {
        return null;
      }

      ParameterizedType parameterizedType=(ParameterizedType)genericParameterTypes[0];
      Type rawType = parameterizedType.getRawType();
      System.out.println("----> rawType=" + rawType);
      Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
      if (actualTypeArguments==genericParameterTypes || actualTypeArguments.length<1) {
        return null;
      }

      for (int i = 0; i < actualTypeArguments.length; i++) {
        Type type = actualTypeArguments[i];
        System.out.println("----> type=" + type);
      }
    } catch (Exception e) {

    }
    return null;
  }

  @Override
  public String toString() {
    return "Person{" +
            "country='" + country + '\'' +
            ", city='" + city + '\'' +
            ", name='" + name + '\'' +
            ", province='" + province + '\'' +
            ", height=" + height +
            '}';
  }
}

