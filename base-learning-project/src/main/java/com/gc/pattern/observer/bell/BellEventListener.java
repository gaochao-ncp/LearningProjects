package com.gc.pattern.observer.bell;

import java.util.EventListener;

/**
 * 抽象观察者类：铃声事件监听器
 * @author gaochao
 * @create 2020-10-09 17:58
 */
public interface BellEventListener extends EventListener {
  /**
   * 听到铃声
   * @param event
   */
  void hearBell(RingEvent event);

}
