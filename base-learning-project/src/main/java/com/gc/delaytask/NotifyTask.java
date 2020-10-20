package com.gc.delaytask;

/**
 * 具体业务逻辑处理类
 *
 * @author: Administrator
 * @date: 2020-10-20 17:55
 * @version: 1.0
 */
public class NotifyTask extends AbstractNotifyTask {

  public NotifyTask() {
  }

  public NotifyTask(NotifyRecord notifyRecord) {
    super(notifyRecord);
  }

  @Override
  public boolean doTask(NotifyRecord notifyRecord) {
    int temp = NotifyStart.count.incrementAndGet() ;
    if( temp % 2 != 0) {
      // 执行成功的逻辑
//      if(temp % 4 == 0 ) {
//        int i = 1/0 ;
//      }
      notifyRecord.setStop(true);
      Utils.print(notifyRecord.getTaskId(), "开始执行任务,发送通知成功");
      return true;
    }else {
      // 执行失败的逻辑
      Utils.print(notifyRecord.getTaskId(), "开始执行任务,发送通知失败");
      return false;
    }
  }
}
