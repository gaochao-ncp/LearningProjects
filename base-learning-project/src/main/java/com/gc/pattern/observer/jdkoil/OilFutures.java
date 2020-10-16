package com.gc.pattern.observer.jdkoil;

import lombok.Data;

import java.util.Observable;
import java.util.Observer;

/**
 * 目标:原油期货
 * test : 利用 Observable 类和 Observer 接口实现原油期货的观察者模式实例。
 * @author gaochao
 * @create 2020-06-02 10:00
 */
@Data
public class OilFutures extends Observable {

  private float price;

  public OilFutures(float price){
    this.price=price;
  }

  /**
   * 触发器:价格变动通知每个观察者
   */
  public void publish(){
    setChanged();
    notifyObservers(this.price);
  }

}
