package com.gc.delaytask;

import cn.hutool.core.date.DateUtil;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 轮询启动类:启动时加载之前没有发送成功的数据进行轮询发送。
 *
 * @author: Administrator
 * @date: 2020-10-20 16:04
 * @version: 1.0
 */
public class NotifyStart {

  /**定义轮询延迟任务队列**/
  public static final DelayQueue<AbstractNotifyTask> tasks = new DelayQueue<AbstractNotifyTask>() ;
  /**线程池**/
  public static ThreadPoolExecutor executorPool = Start.EXECUTOR_POOL;

  //public static AtomicInteger count = new AtomicInteger(0) ;
  public CountDownLatch countDownLatch = new CountDownLatch(1);

  private NotifyStart(){}

  public static NotifyStart start(){
    return Start.INSTANCE_START;
  }

  public static void main(String[] args) {
    System.out.println("====== delay task start ======");
    startInitFromDB();
//    try {
//      countDownLatch.await();
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
  }

  public void run(){
    startInitFromDB();
    try {
      while(true) {
        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
        //如果当前活动线程等于最大线程,不执行任务逻辑;此处判断很重要，避免浪费资源，当有线程空闲时才执行任务
        if(executorPool.getActiveCount() < executorPool.getMaximumPoolSize() ) {
          //获取任务队列中第一个任务
          final AbstractNotifyTask task = tasks.poll();
          if(task != null) {
            System.out.println("当前存活线程数量:" + map.size()+";线程活动数:"+executorPool.getActiveCount());
            //将任务从队列中移除
            tasks.remove(task);
            executorPool.execute(task);
          }
        }
      }
    }catch (Exception e) {
      e.printStackTrace();
    }

    try {
      countDownLatch.wait();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /** 启动时加载发送失败的任务信息 **/
  private static void startInitFromDB() {

  }

  /**
   * 添加一个任务到队列中去,实现轮询机制和重发的时间间隔逻辑类
   */
  public static void addTask(AbstractNotifyTask task) {
    NotifyRecord notifyRecord = task.getNotifyRecord();
    //获取已经通知的次数
    Integer notifyTimes = notifyRecord.getNotifyTimes() ;
    //获取版本号
    int version = notifyRecord.getVersion().intValue();
    //如果值为初始化默认的值
    if(version == 0 ) {
      //设置上次通知时间为当前时间
      notifyRecord.setLastNotifyTime(new Date());
    }
    //获取上次发送通知的时间
    long time = notifyRecord.getLastNotifyTime().getTime() ;
    //最大重发次数
    Integer maxLimitTime = NYConfig.roundTime.size() ;

    // 通知次数小于最大次数 且 停止标志为false才继续执行
    if( (notifyTimes < maxLimitTime) && !notifyRecord.isStop()) {
      //获取发送任务的时间机制
//      Integer nextTime = NYConfig.roundTime.get(notifyTimes+1) ;
//      // 下一次发送任务的时间(距离上一次发送间隔多少[nextTime]分钟在这里进行逻辑设置)
//      time += Utils.getExecTime(nextTime,Utils.SECONDS) ;
//      notifyRecord.setLastNotifyTime(new Date(time));
      //更新执行时间
      task.setExecuteTime(notifyRecord);
      System.out.println("下次执行时间:"+ new Date(task.executeTime) +";ID:"+task.getNotifyParam().getId());
      NotifyStart.tasks.put(task);
    }else { // 轮询机制已经完成,无法在发送信息,施行入库操作。
      Utils.print(notifyRecord.getTaskId(), ":插入数据库");
    }
  }


  private static class Start{
    private static NotifyStart INSTANCE_START = new NotifyStart();
    private static ThreadPoolExecutor EXECUTOR_POOL = new ThreadPoolExecutor(
            20,
            300,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024),
            (r)-> new Thread(r),
            new ThreadPoolExecutor.AbortPolicy());
  }

}
