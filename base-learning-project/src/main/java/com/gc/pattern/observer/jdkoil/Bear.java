package com.gc.pattern.observer.jdkoil;

import java.util.Observable;
import java.util.Observer;

/**
 * 具体观察者类：空方
 * @author gaochao
 * @create 2020-10-09 10:30
 */
public class Bear implements Observer {

  @Override
  public void update(Observable o, Object arg) {
    Float price=((Float)arg).floatValue();
    if(price>0)
    {
      System.out.println("油价上涨"+price+"元，空方伤心了！");
    }
    else
    {
      System.out.println("油价下跌"+(-price)+"元，空方开心了！");
    }
  }
}
