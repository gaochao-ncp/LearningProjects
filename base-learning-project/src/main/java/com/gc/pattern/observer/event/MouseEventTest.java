package com.gc.pattern.observer.event;

/**
 * @author gaochao
 * @create 2020-10-09 15:22
 */
public class MouseEventTest {

  public static void main(String[] args) {
    MouseEventCallback callback = new MouseEventCallback();
    //注册事件
    Mouse mouse = new Mouse();
    mouse.addListener(MouseEventType.ON_CLICK, callback);

    mouse.click();
  }
}
