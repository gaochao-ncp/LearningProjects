package com.gc.pattern.factory.method;

import com.gc.pattern.factory.ICourse;
import com.gc.pattern.factory.JavaCourse;

/**
 * @author gaochao
 * @create 2020-09-19 22:24
 */
public class JavaCourseFactory implements ICourseFactory {

  @Override
  public ICourse create() {
    return new JavaCourse();
  }
}
