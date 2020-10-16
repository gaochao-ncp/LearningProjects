package com.gc.pattern.observer.event;

/**
 * 事件回调
 * @author gaochao
 * @create 2020-10-09 15:21
 */
public class MouseEventCallback {

  public void onClick(Event e){
    System.out.println("===========触发鼠标单击事件==========" + "\n" + e);
  }

}
