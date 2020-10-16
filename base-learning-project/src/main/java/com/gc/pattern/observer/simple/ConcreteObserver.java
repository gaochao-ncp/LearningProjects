package com.gc.pattern.observer.simple;

import lombok.extern.slf4j.Slf4j;

/**
 * 具体观察者
 * @author gaochao
 * @create 2020-06-01 17:14
 */
@Slf4j
public class ConcreteObserver {

  private SimpleEvent event;

  public ConcreteObserver(SimpleEvent event){
    this.event=event;
  }

  public void response() {
    log.info("ConcreteObserver 订阅事件:"+event.getContext());
  }
}
