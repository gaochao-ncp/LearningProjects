package com.gc.pattern.decorator.beverage;

/**
 * @author gaochao
 * @create 2020-09-30 17:49
 */
public class BeverageTest {

  public static void main(String[] args) {
    //冲调一杯Decaf
    Beverage decaf = new DecafBeverage();
    //加 奶泡调料类
    decaf = new WhipDecorator(decaf);
    //加 豆浆
    decaf = new SoyDecorator(decaf);

    System.out.println(decaf.getDescription());
    System.out.println(decaf.cost());

  }
}
