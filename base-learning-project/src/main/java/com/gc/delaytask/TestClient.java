package com.gc.delaytask;

import cn.hutool.core.date.DateUtil;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author: Administrator
 * @date: 2020-10-23 16:04
 * @version: 1.0
 */
public class TestClient {


  public static void main(String[] args) {
    System.out.println(TimeUnit.SECONDS.name());
    // 查询状态和通知次数符合以下条件的数据进行通知
    System.out.println("start task... ");
    Integer pageNum = 0 ;
    //获取数据库失败任务的总记录页数
    Integer endNum = 3 ;
    while(pageNum <= endNum ) {
      //分页查询数据库，加载发送失败的任务信息
      System.out.println("query database before fail data task");
      //将任务信息放入到任务队列中去
      NotifyRecord notifyRecord = new NotifyRecord(UUID.randomUUID().toString(),new Date()) ;
      NotifyParam param = new NotifyParam();
      param.setId(String.valueOf(pageNum));
      AbstractNotifyTask task = new NotifyTask(notifyRecord,param);
      NotifyStart.addTask(task);
      //开始读取下一页
      pageNum =pageNum+1;
    }

    //异步执行
//    NotifyStart.executorPool.execute(()->{
//
//    });

    NotifyStart.start().run();

    System.out.println("end task... ");

  }

}
