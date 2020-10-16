package com.gc.pattern.observer.event;

/**
 * 鼠标
 * @author gaochao
 * @create 2020-10-09 15:21
 */
public class Mouse extends EventListener {

  public void click(){
    System.out.println("调用单击方法");
    this.trigger(MouseEventType.ON_CLICK);
  }
}
