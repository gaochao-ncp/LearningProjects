package com.gc.delaytask;

import java.io.Serializable;
import java.util.Date;

/**
 * 轮询记录基类:用于记录轮询时的信息
 * @author: Administrator
 * @date: 2020-10-20 16:00
 * @version: 1.0
 */
public class NotifyRecord implements Serializable {
  /**唯一标识**/
  protected String taskId;
  /**创建时间**/
  protected Date createTime ;
  /**版本号**/
  protected Integer version = 0 ;
  /**已发通知次数**/
  protected Integer notifyTimes = 0 ;
  /**上次发送的通知时间**/
  protected Date lastNotifyTime ;
  /**最大重发机制通知次数**/
  protected Integer maxLimitTimes = 5 ;
  /**是否暂停标志:true-停止;false-不停止**/
  protected boolean stop = false;

  public NotifyRecord(){}

  public NotifyRecord( String taskId, Date lastNotifyTime) {
    this.taskId = taskId;
    this.lastNotifyTime = lastNotifyTime;
    this.createTime=new Date();
  }

  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public Integer getNotifyTimes() {
    return notifyTimes;
  }

  public void setNotifyTimes(Integer notifyTimes) {
    this.notifyTimes = notifyTimes;
  }

  public Date getLastNotifyTime() {
    return lastNotifyTime;
  }

  public void setLastNotifyTime(Date lastNotifyTime) {
    this.lastNotifyTime = lastNotifyTime;
  }

  public Integer getMaxLimitTimes() {
    return maxLimitTimes;
  }

  public void setMaxLimitTimes(Integer maxLimitTimes) {
    this.maxLimitTimes = maxLimitTimes;
  }

  public boolean isStop() {
    return stop;
  }

  public void setStop(boolean stop) {
    this.stop = stop;
  }
}
