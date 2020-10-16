package com.gc.pattern.decorator.beverage;

import java.math.BigDecimal;

/**
 * 摩卡调料类
 * @author gaochao
 * @create 2020-09-16 0:13
 */
public class MochaDecorator extends CondimentDecorator {

  public MochaDecorator(Beverage beverage) {
    super(beverage);
  }

  @Override
  public String getDescription() {
    return super.getDescription()+" +摩卡调料";
  }

  @Override
  public BigDecimal cost() {
    return super.cost().add(new BigDecimal(3));
  }

}
