package com.gc.pattern.decorator.pancake;

/**
 * @author gaochao
 * @create 2020-09-30 16:05
 */
public class EggDecorator extends BatterCakeDecorator {
  @Override
  protected String getDes() {
    return "加鸡蛋";
  }

  public EggDecorator(BatterCake batterCake) {
    super(batterCake);
  }

  @Override
  protected String getName() {
    return super.getName()+"+ 1鸡蛋";
  }

  @Override
  protected double getPrice() {
    return super.getPrice()+2;
  }
}
