package com.gc.pattern.decorator.beverage;

import java.math.BigDecimal;

/**
 * 调料装饰着抽象类
 * @author gaochao
 * @create 2020-09-16 0:12
 */
public abstract class CondimentDecorator extends Beverage {

  private Beverage beverage;

  public CondimentDecorator(Beverage beverage){
    this.beverage=beverage;
  }

  @Override
  public String getDescription() {
    return beverage.getDescription();
  }

  @Override
  public BigDecimal cost() {
    return beverage.cost();
  }
}
