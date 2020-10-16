package com.gc.mybatis.reflector;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author gaochao
 * @create 2020-08-07 10:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReflectorTestSuper {

  private String name = "这是测试类的父类";

  public static ConcurrentHashMap<String,ReflectorTestSuper> test = new ConcurrentHashMap<>();
  public static HashMap<String,ReflectorTestSuper> testMap = new HashMap<>();


  public List<String> superTest(String str){
    return Arrays.asList(str);
  }


  private ReflectorTestSuper findClass(String key){
    return test.computeIfAbsent(key,
            ReflectorTestSuper::new);//函数式编程接口
  }

  public static void main(String[] args) {
    ReflectorTestSuper test = new ReflectorTestSuper();
    System.out.println("test.findClass(\"1\") = " + test.findClass("1").getName());

  }


}
