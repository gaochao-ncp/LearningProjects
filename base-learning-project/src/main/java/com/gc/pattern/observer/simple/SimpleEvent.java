package com.gc.pattern.observer.simple;

import lombok.Data;

/**
 * 需要进行通知的事件
 * @author gaochao
 * @create 2020-10-09 16:44
 */
@Data
public class SimpleEvent {

  /** 事件类型 **/
  public static final String EVENT_1 = "EVENT_1";
  public static final String EVENT_2 = "EVENT_2";
  public static final String EVENT_3 = "EVENT_3";

  /** 事件内容 **/
  private String context;

  public SimpleEvent(String context){
    this.context=context;
  }

}
