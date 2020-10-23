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

  /**触发时间**/
  protected long executeTime ;

  /**通知消息记录类**/
  protected NotifyRecord notifyRecord ;
  protected NotifyParam notifyParam;

  public AbstractNotifyTask() {}

  public AbstractNotifyTask(NotifyRecord notifyRecord,NotifyParam notifyParam) {
    this.notifyRecord = notifyRecord;
    this.notifyParam = notifyParam;
    setExecuteTime(notifyRecord);
  }

  @Override
  public void run() {
    int notifyTimes = notifyRecord.getNotifyTimes() ;
    //开始执行任务,发送通知
    try {
      //模拟业务
      boolean doTaskFlag = doTask(notifyRecord, notifyParam);
      if (doTaskFlag){
        notifyRecord.setStop(true);
        //TODO 关于轮询的记录信息需不需要实现?
      }
      if(!doTaskFlag && !notifyRecord.isStop()) {
        //重发次数加+1
        notifyRecord.setNotifyTimes(notifyTimes+1);
        //添加任务到队列中去
        NotifyStart.addTask(this);
      }
    }catch (Exception e) {
      Utils.print("", "执行任务出现异常"+e.getMessage());
      notifyRecord.setNotifyTimes(notifyTimes+1);
      //添加任务到队列中去
      NotifyStart.addTask(this);
    }
  }

  /**
   * 业务逻辑:设置为抽象方法,提供给子类实现.
   * @param notifyRecord
   * @return
   */
  public abstract boolean doTask(NotifyRecord notifyRecord,NotifyParam notifyParam);

  public void setExecuteTime(NotifyRecord notifyRecord ) {
    long lastTime = notifyRecord.getLastNotifyTime().getTime();
    Integer nextNotifyTime = NYConfig.roundTime.get(notifyRecord.getNotifyTimes());
    this.executeTime = (nextNotifyTime == null ? 0 : Utils.getExecTime(nextNotifyTime,Utils.SECONDS)) + lastTime;
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

  public long getExecuteTime() {
    return executeTime;
  }

  public NotifyRecord getNotifyRecord() {
    return notifyRecord;
  }

  public NotifyParam getNotifyParam() {
    return notifyParam;
  }

}
