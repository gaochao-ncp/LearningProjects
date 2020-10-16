package com.gc.pattern.decorator.beverage;

import java.math.BigDecimal;

/**
 * 豆浆调料类
 * @author gaochao
 * @create 2020-09-16 0:13
 */
public class SoyDecorator extends CondimentDecorator {

  public SoyDecorator(Beverage beverage) {
    super(beverage);
  }

  @Override
  public String getDescription() {
    return super.getDescription()+" +豆浆调料";
  }

  @Override
  public BigDecimal cost() {
    return super.cost().add(new BigDecimal(3));
  }
}
