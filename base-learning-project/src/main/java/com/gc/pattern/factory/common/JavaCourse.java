package com.gc.pattern.factory.common;

/**
 * java视频
 * @author gaochao
 * @create 2020-09-19 21:00
 */
public class JavaCourse implements ICourse {
  @Override
  public void record() {
    System.out.println("录制java视频");
  }
}
