package com.gc.pattern.observer.bell;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;

/**
 * test: 利用观察者模式设计一个学校铃声的事件处理程序。
 * @author gaochao
 * @create 2020-06-01 22:06
 */

/**
 * 铃声事件:用于封装事件源及一些与事件相关的参数
 */
@Getter
@Setter
public class RingEvent extends EventObject {

  /**
   * true表示上课铃声,false表示下课铃声
   */
  private boolean sound;

  /**
   * Constructs a prototypical Event.
   *
   * @param source The object on which the Event initially occurred.
   * @throws IllegalArgumentException if source is null.
   */
  public RingEvent(Object source,boolean sound) {
    super(source);
    this.sound = sound;
  }

}

/**
 * 事件源:铃.目标类
 */
@Slf4j
class BellEventSource{

  private List<BellEventListener> listeners ;//监听器容器

  public BellEventSource(){
    listeners = new ArrayList<>();
  }

  //给事件源绑定监听器
  public BellEventSource addPersonListener(BellEventListener ren)
  {
    listeners.add(ren);
    //链式调用的关键
    return this;
  }

  //触发器
  public void ring(boolean sound){
    String type = sound?"上课铃响":"下课铃响";
    log.info(type);
    RingEvent e = new RingEvent(this, sound);

  }

  //事件发生,通知观察者
  protected void notifyListener(RingEvent e){
    for (BellEventListener listener : listeners){
      listener.hearBell(e);
    }
  }

}



/**
 * 具体观察者:老师事件监听器
 */
@Slf4j
class TeacherEvenListener implements BellEventListener{

  @Override
  public void hearBell(RingEvent event) {

    if (event.isSound()){
      log.info("上课铃声响了,老师开始上课了");
    }else {
      log.info("下课铃声响了,老师下课了");
    }

  }
}

/**
 * 具体观察者:学生事件监听器
 */
@Slf4j
class StudentEvenListener implements BellEventListener{

  @Override
  public void hearBell(RingEvent event) {

    if (event.isSound()){
      log.info("上课铃声响了,学生说老师好");
    }else {
      log.info("下课铃声响了,学生说老师再见");
    }

  }
}
