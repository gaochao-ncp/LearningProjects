package com.gc.delaytask;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Administrator
 * @date: 2020-10-20 16:13
 * @version: 1.0
 */
public class Utils {

  public static String SECONDS = "SECONDS";
  public static String MINUTES = "MINUTES";

  /**获取当前时间戳**/
  public static String getCurrentTimes() {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) ;
  }
  /**打印任务信息**/
  public static void print(String taskId , String content ) {
    System.out.println(taskId +"  "+Utils.getCurrentTimes()+content);
  }

  /**
   * 时间转换成对应的long数据类型
   * @param time
   * @param type
   * @return
   */
  public static long getExecTime(Integer time,String type){
    if (SECONDS.equals(type)){
      return time * 1000;
    }else if (MINUTES.equals(type)){
      return time * 1000 * 60;
    }
    return 0L;
  }
}
