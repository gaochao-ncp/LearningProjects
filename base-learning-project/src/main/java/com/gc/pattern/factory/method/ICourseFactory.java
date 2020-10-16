package com.gc.pattern.factory.method;

import com.gc.pattern.factory.ICourse;
import com.gc.pattern.factory.JavaCourse;

/**
 * 超级接口:不实现逻辑,交给其子类实现
 * 分别创建以下课程对应的工厂
 * @
 * 注意:这个类也可以是一个抽象类:将创建对象的公共部分封装在统一的一个方法中来
 * @author gaochao
 * @create 2020-09-19 22:22
 */
public interface ICourseFactory {

  ICourse create();

  public static void main(String[] args) {

    ICourseFactory factory = new JavaCourseFactory();
    ICourse course = factory.create();

    course.record();

    factory= new PhpCourseFactory();
    course = factory.create();
    course.record();

    factory=new PythonCourseFactory();
    factory.create();
    course = factory.create();
    course.record();

  }

}
