package com.gc.pattern.decorator.pancake;

/**
 * 煎饼的装饰者
 * @author gaochao
 * @create 2020-09-29 16:48
 */
public abstract class BatterCakeDecorator extends BatterCake {
  /**
   * 持有煎饼基本套餐的引用,静态代理,委派
   */
  private BatterCake batterCake;

  public BatterCakeDecorator(BatterCake batterCake){
    this.batterCake=batterCake;
  }

  protected abstract String getDes();

  @Override
  protected String getName() {
    return batterCake.getName();
  }


  @Override
  protected double getPrice() {
    return batterCake.getPrice();
  }
}
