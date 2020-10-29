package com.gc.pattern.factory.method;

import com.gc.pattern.factory.common.ICourse;
import com.gc.pattern.factory.common.PhpCourse;

/**
 * @author gaochao
 * @create 2020-09-19 22:24
 */
public class PhpCourseFactory implements ICourseFactory {

  @Override
  public ICourse create() {
    return new PhpCourse();
  }
}
