package com.gc.pattern.decorator.pancake;

/**
 * 基本煎饼套餐
 * @author gaochao
 * @create 2020-09-29 16:46
 */
public class BaseBatterCake extends BatterCake {

  @Override
  protected String getName() {
    return "基本煎饼套餐";
  }

  @Override
  protected double getPrice() {
    return 5;
  }
}
