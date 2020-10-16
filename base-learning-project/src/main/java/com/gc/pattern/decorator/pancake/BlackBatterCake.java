package com.gc.pattern.decorator.pancake;

/**
 * 黑米煎饼
 * @author gaochao
 * @create 2020-09-30 16:13
 */
public class BlackBatterCake extends BatterCake {

  @Override
  protected String getName() {
    return "黑米煎饼";
  }

  @Override
  protected double getPrice() {
    return 6;
  }
}
