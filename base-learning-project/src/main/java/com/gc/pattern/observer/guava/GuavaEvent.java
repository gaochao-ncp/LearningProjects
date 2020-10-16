package com.gc.pattern.observer.guava;

import com.google.common.eventbus.Subscribe;

/**
 * guava类库的应用
 * @author gaochao
 * @create 2020-10-09 16:16
 */
public class GuavaEvent {

  @Subscribe
  public void subscribe(String str){
    //业务逻辑
    System.out.println("执行 subscribe 方法,传入的参数是:" + str);
  }

}
