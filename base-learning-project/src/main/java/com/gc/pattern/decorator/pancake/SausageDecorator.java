package com.gc.pattern.decorator.pancake;

/**
 * @author gaochao
 * @create 2020-09-30 16:05
 */
public class SausageDecorator extends BatterCakeDecorator {
  @Override
  protected String getDes() {
    return "加香肠";
  }

  public SausageDecorator(BatterCake batterCake) {
    super(batterCake);
  }

  @Override
  protected String getName() {
    return super.getName()+"+ 1香肠";
  }

  @Override
  protected double getPrice() {
    return super.getPrice()+3;
  }
}
