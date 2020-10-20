package com.gc.delaytask;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 业务逻辑处理类
 * @author: Administrator
 * @date: 2020-10-20 16:03
 * @version: 1.0
 */
public abstract class AbstractNotifyTask implements Delayed, Runnable{

  protected long executeTime ;

  /**通知消息记录类**/
  protected NotifyRecord notifyRecord ;

  public AbstractNotifyTask() {}

  public AbstractNotifyTask(NotifyRecord notifyRecord) {
    this.notifyRecord = notifyRecord;
    this.executeTime = getExecuteTime(notifyRecord);
  }

  @Override
  public void run() {
    int notifyTimes = notifyRecord.getNotifyTimes() ;
    //开始执行任务,发送通知
    try {
      //模拟业务
      if(!doTask(notifyRecord) && !notifyRecord.isStop()) {
        //重发次数加+1
        notifyRecord.setNotifyTimes(notifyTimes+1);
        //添加任务到队列中去
        NotifyStart.addTask(notifyRecord);
      }
    }catch (Exception e) {
      Utils.print("", "执行任务出现异常"+e.getMessage());
      notifyRecord.setNotifyTimes(notifyTimes+1);
      //添加任务到队列中去
      NotifyStart.addTask(notifyRecord);
    }
  }

  /**
   * 业务逻辑:设置为抽象方法,提供给子类实现.
   * @return
   */
  public abstract boolean doTask(NotifyRecord notifyRecord);

  private long getExecuteTime(NotifyRecord notifyRecord ) {
    long lastTime = notifyRecord.getLastNotifyTime().getTime();
    Integer nextNotifyTime = NYConfig.roundTime.get(notifyRecord.getNotifyTimes());
    return (nextNotifyTime == null ? 0 : nextNotifyTime * 1000) + lastTime;
  }

  @Override
  public int compareTo(Delayed delayed) {
    if(delayed instanceof AbstractNotifyTask) {
      AbstractNotifyTask o = (AbstractNotifyTask) delayed;
      return this.executeTime > o.executeTime ? 1 : (this.executeTime < o.executeTime ? -1 : 0 ) ;
    }
    return 0;
  }

  @Override
  public long getDelay(TimeUnit unit) {
    return unit.convert(this.executeTime - System.currentTimeMillis(), TimeUnit.SECONDS );
  }
}
