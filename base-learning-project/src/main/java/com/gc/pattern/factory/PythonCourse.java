package com.gc.pattern.factory;

/**
 * Python视频
 * @author gaochao
 * @create 2020-09-19 21:00
 */
public class PythonCourse implements ICourse {
  @Override
  public void record() {
    System.out.println("录制Python视频");
  }
}
