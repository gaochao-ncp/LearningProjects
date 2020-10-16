package com.gc.pattern.observer.guava;

import com.google.common.eventbus.EventBus;

/**
 * @author gaochao
 * @create 2020-10-09 16:17
 */
public class GuavaEventTest {

  public static void main(String[] args) {

    //事件总线
    EventBus eventBus = new EventBus();
    GuavaEvent event = new GuavaEvent();
    eventBus.register(event);
    eventBus.post("tom");


  }
}
