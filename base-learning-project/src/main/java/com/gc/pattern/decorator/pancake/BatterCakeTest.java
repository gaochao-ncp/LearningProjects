package com.gc.pattern.decorator.pancake;

/**
 * @author gaochao
 * @create 2020-09-30 16:08
 */
public class BatterCakeTest {

  public static void main(String[] args) {
    BatterCake b = new BaseBatterCake();


    b = new EggDecorator(b);

    b = new SausageDecorator(b);

    System.out.println(b.getName()+",价格为:"+b.getPrice());

  }

}
