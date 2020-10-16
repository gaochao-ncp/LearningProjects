package com.gc.pattern.observer.event;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * 监听器的一种包装,标准事件源格式的定义
 * @author gaochao
 * @create 2020-10-09 14:28
 */
@Data
public class Event {
  /**
   * 事件源,事件是由谁发起的保存起来
   */
  private Object source;
  /**
   * 事件触发,要通知谁
   */
  private Object target;
  /**
   * 事件触发,要做什么动作,回调
   */
  private Method callback;
  /**
   * 事件的名称,触发的是什么事件
   */
  private String trigger;
  /**
   * 事件触发的时间
   */
  private long time;

  public Event(Object target,Method callback){
    this.target=target;
    this.callback=callback;
  }
}
