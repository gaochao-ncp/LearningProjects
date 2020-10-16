package com.gc.mybatis.reflector;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.reflection.Reflector;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author gaochao
 * @create 2020-08-06 18:03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReflectorTest extends ReflectorTestSuper {

  /**
   * 一个属性的get方法如果写get和is开头的写两个的话会报错.
   */
  private String description = "这是反射的测试类";
  private String work = "学习源码使我快乐";
  private String name = "这是子类";

  public void addUniqueMethods(Map<String, Method> uniqueMethods, Method[] methods){

  }

  @Override
  public ArrayList<String> superTest(String str){
    return new ArrayList<>();
  }

  public static void main(String[] args) {
    Reflector f = new Reflector(ReflectorTest.class);
    System.out.println("f.getGetablePropertyNames() = " + f.getGetablePropertyNames());


  }
}
