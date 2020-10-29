package com.gc.pattern.proxy.common;

/**
 * 实现了 {@link Person} 接口的女孩类
 * @author gaochao
 * @create 2020-09-23 22:06
 */
public class Girl implements Person {
  @Override
  public void findLove() {
    System.out.println("找对象要求 180cm 有车有房");
  }
}
