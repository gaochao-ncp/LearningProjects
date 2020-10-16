package com.gc.pattern.decorator.beverage;

import java.math.BigDecimal;

/**
 * 奶泡调料类
 * @author gaochao
 * @create 2020-09-16 0:13
 */
public class WhipDecorator extends CondimentDecorator {

  public WhipDecorator(Beverage beverage) {
    super(beverage);
  }

  @Override
  public String getDescription() {
    return super.getDescription()+" +奶泡调料";
  }

  @Override
  public BigDecimal cost() {
    return super.cost().add(new BigDecimal(3));
  }
}
