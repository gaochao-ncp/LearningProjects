package com.gc.pattern.decorator.beverage;

import java.math.BigDecimal;

/**
 * 低咖啡因咖啡类
 * @author gaochao
 * @create 2020-09-16 0:10
 */
public class DecafBeverage extends Beverage {

  @Override
  public String getDescription() {
    return "低咖啡因咖啡";
  }

  @Override
  public BigDecimal cost() {
    return new BigDecimal(2);
  }
}
