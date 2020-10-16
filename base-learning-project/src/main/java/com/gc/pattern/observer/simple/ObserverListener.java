package com.gc.pattern.observer.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * 抽象目标(抽象被观察者)
 * @author gaochao
 * @create 2020-06-01 17:16
 */
public class ObserverListener {

  /**
   * 观察者列表
   */
  protected Map<String,ConcreteObserver> observers = new HashMap();

  public void addObserver(String type,String context){
    SimpleEvent simpleEvent = new SimpleEvent(context);
    ConcreteObserver observer = new ConcreteObserver(simpleEvent);
    addObserver(type,observer);
  }


  /**
   * 新增观察者
   * @param observer
   */
  public void addObserver(String type,ConcreteObserver observer){
    observers.put(type,observer);
  }

  /**
   * 通知观察者方法
   */
  public void publishObserver(String type){
    if (observers.containsKey(type)){
      observers.get(type).response();
    }
  }
}
