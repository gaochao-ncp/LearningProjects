package com.gc.pattern.decorator.beverage;

import java.math.BigDecimal;

/**
 * 饮料抽象类
 * @author gaochao
 * @create 2020-09-16 0:04
 */
public abstract class Beverage {

  /** 描述 **/
  public abstract String getDescription();

  /** 费用 **/
  public abstract BigDecimal cost();


}
