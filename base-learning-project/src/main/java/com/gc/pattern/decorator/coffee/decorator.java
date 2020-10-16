package com.gc.pattern.decorator.coffee;

/**
 * 装饰器模式例子1：咖啡的实例
 */

/**
 * 抽象构件：咖啡
 *
 */
interface Coffee {

  /**
   * 获取配料
   * @return
   */
  String getDesc();

  /**
   * 获取价格
   * @return
   */
  double getPrice();

}

/**
 * 具体构件:原味咖啡
 */
class SimpleCoffee implements Coffee{


  @Override
  public String getDesc() {
    return "原味咖啡";
  }

  @Override
  public double getPrice() {
    return 1;
  }
}

/**
 * 具体构件:摩卡咖啡
 */
class MokaCoffee implements Coffee{


  @Override
  public String getDesc() {
    return "摩卡咖啡";
  }

  @Override
  public double getPrice() {
    return 2;
  }
}

/**
 * 装饰器：咖啡的装饰器:持有抽象对象的引用
 */
class CoffeeDecorator implements Coffee{

  private Coffee coffee;

  public CoffeeDecorator(Coffee coffee){
    this.coffee=coffee;
  }

  @Override
  public String getDesc() {
    return coffee.getDesc();
  }

  @Override
  public double getPrice() {
    return coffee.getPrice();
  }
}

/**
 * 具体装饰器：加牛奶
 */
class WithMike extends CoffeeDecorator{

  public WithMike(Coffee coffee) {
    super(coffee);
  }

  @Override
  public String getDesc() {
    return super.getDesc()+ "-->加牛奶";
  }

  @Override
  public double getPrice() {
    return super.getPrice()+1;
  }

}

/**
 * 具体装饰器：加糖
 */
class WithSugar extends CoffeeDecorator{

  public WithSugar(Coffee coffee) {
    super(coffee);
  }

  @Override
  public String getDesc() {
    return super.getDesc()+ "-->加糖";
  }

  @Override
  public double getPrice() {
    return super.getPrice()+2;
  }

}
