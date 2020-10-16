package com.gc.pattern.decorator.pancake;

/**
 * 煎饼
 * 抽象出一个煎饼的对象
 * @author gaochao
 * @create 2020-09-29 16:41
 */
public abstract class BatterCake {

  /**
   * 获取名称
   * @return
   */
  protected abstract String getName();

  /**
   * 获取价格
   * @return
   */
  protected abstract double getPrice();

}
