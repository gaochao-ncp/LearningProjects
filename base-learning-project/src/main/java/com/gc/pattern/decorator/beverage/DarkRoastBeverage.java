package com.gc.pattern.decorator.beverage;

import java.math.BigDecimal;

/**
 * 深焙咖啡类
 * @author gaochao
 * @create 2020-09-16 0:09
 */
public class DarkRoastBeverage extends Beverage {
  @Override
  public String getDescription() {
    return "深焙咖啡类";
  }

  @Override
  public BigDecimal cost() {
    return new BigDecimal(3);
  }
}
