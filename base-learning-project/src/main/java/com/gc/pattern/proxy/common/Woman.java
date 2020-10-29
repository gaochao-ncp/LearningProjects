package com.gc.pattern.proxy.common;

/**
 * 没有实现接口的单身女性
 * @author gaochao
 * @create 2020-09-25 11:52
 */
public class Woman {

  public void findLove(){
    System.out.println("找对象要求是个男的就行");
  }

  public void findHouse(){
    System.out.println("找房子入职");
  }

  /**
   * final方法无法被cglib动态代理
   */
  public final void eat(){
    System.out.println("每天固定吃低脂减肥餐");
  }

  public static void son(){
    System.out.println("去KTV唱歌!");
  }

}
