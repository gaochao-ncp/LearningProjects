package com.gc.pattern.observer.event;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 事件监听器,观察者的桥梁
 * @author gaochao
 * @create 2020-10-09 14:57
 */
@Slf4j
public class EventListener {

  /**
   * jdk底层的Listener也是这样设计的
   */
  protected Map<String,Event> events = new HashMap<>();

  /**
   * 注册事件
   * @param eventType
   * @param target
   */
  public void addListener(String eventType, Object target){
    try {
      //获得回调方法
      Method callback = target.getClass().getDeclaredMethod("on" + toUpperFirstCase(eventType), Event.class);
      this.addListener(eventType,target,callback);
    } catch (NoSuchMethodException e) {
      log.error("addListener error",e);
    }
  }

  public void addListener(String eventType, Object target, Method callback){
    // 注册事件
    events.put(eventType,new Event(target,callback));
  }

  /**
   * 触发:只要有动作就触发
   * @param event
   */
  private void trigger(Event event){
    event.setSource(this);
    event.setTime(System.currentTimeMillis());

    //回调
    if (event.getCallback() != null){
      try {
        // 参数:被调用的对象;参数为Event
        event.getCallback().invoke(event.getTarget(),event);
      } catch (Exception e) {
        log.error("trigger error",e);
      }
    }
  }

  /**
   * 触发:通过事件名称触发
   * @param trigger
   */
  protected void trigger(String trigger){
    if (this.events.containsKey(trigger)){
      trigger(events.get(trigger));
    }
  }

  /**
   * 逻辑处理的私有方法，首字母大写
   * @param str
   * @return
   */
  private String toUpperFirstCase(String str){
    char[] chars = str.toCharArray();
    chars[0] -= 32;
    return String.valueOf(chars);
  }

}
