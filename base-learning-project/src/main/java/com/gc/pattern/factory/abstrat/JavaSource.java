package com.gc.pattern.factory.abstrat;

/**
 * @author gaochao
 * @create 2020-09-20 0:41
 */
public class JavaSource implements ISource {
  @Override
  public ISource creatSource() {
    System.out.println("java 源码");
    return new JavaSource();
  }
}
