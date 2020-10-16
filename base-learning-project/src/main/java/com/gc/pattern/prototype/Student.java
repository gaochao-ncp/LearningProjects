package com.gc.pattern.prototype;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 设计模式的简单应用
 * 利用原型模式解析xml字符串到实体bean中区
 * @author gaochao
 * @create 2020-09-18 0:12
 */
@Data
public class Student {
  private String name;
  private int age;
  private List<String> info;


  //原型模式
  public static void main(String[] args) {

    String json ="{'name':'网站',     'age':3,     'info':[ 'Android', 'Google 搜索', 'Google 翻译' ] }";

    //命名保持一样的话可以直接映射到实体类上面,实体类为nameFirst json数据里面可以是NAME_FIRST name_first nameFirst 都可以
    Student person = JSONObject.parseObject(json, Student.class);

    System.out.println(person.getAge()+"-"+ person.getName()+"-"+ person.getInfo().toString());

    //map转json字符串
    HashMap<String,Object> map = new HashMap<>();
    map.put("name","网站");
    map.put("age",3);
    List<String> info = new ArrayList<>();
    info.add("Android");
    info.add("Google 搜索");
    info.add("Google 翻译");
    map.put("info",info);
    Student person1 = objectToBean(map, Student.class);
    System.out.println(JSONObject.toJSONString(map));
    System.out.println(person.toString());


  }

  /**
   * 将对象转换为特定的实体类
   * @param o
   * @param <T>
   * @return
   */
  private static <T> T objectToBean(Object o,Class<T> clazz){
    String str = JSONObject.toJSONString(o);
    return JSONObject.parseObject(str,clazz);
  }

}
