package com.gc.delaytask;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Administrator
 * @date: 2020-10-20 16:13
 * @version: 1.0
 */
public class Utils {

  /**获取当前时间戳**/
  public static String getCurrentTimes() {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) ;
  }
  /**打印任务信息**/
  public static void print(String taskId , String content ) {
    System.out.println(taskId +"  "+Utils.getCurrentTimes()+content);
  }
}
