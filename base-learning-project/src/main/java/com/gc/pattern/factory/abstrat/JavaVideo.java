package com.gc.pattern.factory.abstrat;

/**
 * @author gaochao
 * @create 2020-09-20 0:40
 */
public class JavaVideo implements IVideo {
  @Override
  public IVideo creatVideo() {
    System.out.println("Java 视频 ");
    return new JavaVideo();
  }
}
