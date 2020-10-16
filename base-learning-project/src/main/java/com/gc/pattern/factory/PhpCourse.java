package com.gc.pattern.factory;

/**
 * php视频
 * @author gaochao
 * @create 2020-09-19 21:00
 */
public class PhpCourse implements ICourse {
  @Override
  public void record() {
    System.out.println("录制php视频");
  }
}
