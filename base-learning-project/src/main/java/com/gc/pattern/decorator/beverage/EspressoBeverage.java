package com.gc.pattern.decorator.beverage;

import java.math.BigDecimal;

/**
 * 浓缩咖啡类
 * @author gaochao
 * @create 2020-09-16 0:11
 */
public class EspressoBeverage extends Beverage {

  @Override
  public String getDescription() {
    return "浓缩咖啡类";
  }

  @Override
  public BigDecimal cost() {
    return new BigDecimal(7);
  }
}
