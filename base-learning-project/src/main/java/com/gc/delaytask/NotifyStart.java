package com.gc.delaytask;

import java.util.Date;
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
  public static DelayQueue<AbstractNotifyTask> tasks = new DelayQueue<AbstractNotifyTask>() ;
  /**线程池**/
  private static ThreadPoolExecutor executorPool = new ThreadPoolExecutor(
          20,
          300,
          0L,
          TimeUnit.MILLISECONDS,
          new LinkedBlockingQueue<Runnable>(1024),
          new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
              return new Thread(r);
            }
          },
          new ThreadPoolExecutor.AbortPolicy());

  public static AtomicInteger count = new AtomicInteger(0) ;
  private static CountDownLatch countDownLatch = new CountDownLatch(1);

  public static void main(String[] args) {
    System.out.println("====== delay task start ======");
    startInitFromDB();
    startThread();
    try {
      countDownLatch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**开始执行任务**/
  private static void startThread() {
    try {
      while(true) {
        //如果当前活动线程等于最大线程,不执行任务逻辑;此处判断很重要，避免浪费资源，当有线程空闲时才执行任务
        if(executorPool.getActiveCount() < executorPool.getMaximumPoolSize() ) {
          //获取任务队列中第一个任务
          final AbstractNotifyTask task = tasks.poll();
          if(task != null) {
            executorPool.execute(()-> {
                System.out.println("线程活动数:"+executorPool.getActiveCount());
                //将任务从队列中移除
                tasks.remove(task);
                //执行任务
                task.run();
            });
          }
        }
      }
    }catch (Exception e) {
      e.printStackTrace();
    }
  }

  /** 启动时加载发送失败的任务信息 **/
  private static void startInitFromDB() {
    // 查询状态和通知次数符合以下条件的数据进行通知
    System.out.println("get data from database ");
    Integer pageNum = 1 ;
    //获取数据库失败任务的总记录页数
    Integer endNum = 20 ;
    while(pageNum <= endNum ) {
      //分页查询数据库，加载发送失败的任务信息
      System.out.println("query database before fail data task");
      //将任务信息放入到任务队列中去
      NotifyRecord notifyRecord = new NotifyRecord(UUID.randomUUID().toString(),new Date()) ;
      //notifyRecord.setLastNotifyTime(new Date());
      addTask(notifyRecord);
      //开始读取下一页
      pageNum =pageNum+1;
    }
  }

  /**
   * 添加一个任务到队列中去,实现轮询机制和重发的时间间隔逻辑类
   * @param notifyRecord
   */
  public static void addTask(NotifyRecord notifyRecord ) {
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
      Integer nextTime = NYConfig.roundTime.get(notifyTimes+1) ;
      // 下一次发送任务的时间(距离上一次发送间隔多少[nextTime]分钟在这里进行逻辑设置)
      time += 1000 * 60 * nextTime ;
      notifyRecord.setLastNotifyTime(new Date(time));
      //添加到任务队列中去 tasks 延迟队列DelayQueue一个对象
      NotifyStart.tasks.put(new NotifyTask(notifyRecord));
    }else { // 轮询机制已经完成,无法在发送信息,施行入库操作。
      Utils.print(notifyRecord.getTaskId(), ":插入数据库");
    }
  }

}
