package com.gc.pattern.factory.simple;

import com.gc.pattern.factory.ICourse;
import com.gc.pattern.factory.JavaCourse;
import com.gc.pattern.factory.PhpCourse;
import com.gc.pattern.factory.PythonCourse;

/**
 * 简单课程工厂
 * 违背开闭原则:每次新增产品需要修改工厂代码;
 * 缺点:工厂职责过重,不易于拓展过于复杂的产品结构
 * jdk中应用:
 * {@link java.util.Calendar}
 * {@link org.slf4j.LoggerFactory}
 * @author gaochao
 * @create 2020-09-19 21:02
 */
public class SimpleCourseFactory {

  /**
   * 传入类型来创建对应的产品
   * @param type
   * @return
   */
  public static ICourse creatCourse(String type){
    if ("java".equals(type)){
      return new JavaCourse();
    }else if ("python".equals(type)){
      return new PythonCourse();
    }else if ("php".equals(type)){
      return new PhpCourse();
    }
    return null;
  }

  /**
   * 传入对应产品的类来创建
   * @param clz
   * @return
   */
  public static ICourse creatCourse(Class<? extends ICourse> clz){
    try {
      return clz.newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }
      return null;
  }

  /**
   * 传入对应产品的全类名来创建
   * @param className
   * @return
   */
  public static ICourse creatCourseByFullClassName(String className){
    try {
      return (ICourse)Class.forName(className).newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }
      return null;
  }

  public static void main(String[] args) {
    ICourse java = SimpleCourseFactory.creatCourse("java");
    java.record();
    ICourse php = SimpleCourseFactory.creatCourseByFullClassName("com.gc.pattern.factory.PhpCourse");
    php.record();
    ICourse python = SimpleCourseFactory.creatCourse(PythonCourse.class);
    python.record();
  }

}
