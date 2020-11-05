package com.gc.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 日期操作类
 *
 * @author: Administrator
 * @date: 2020-10-19 15:21
 * @version: 1.0
 */
public class DateTest {

  /**
   * 对当前日期进行计算
   * @param pattern
   * @param field
   * @param amount
   * @return
   */
  public static String calculateNow(String pattern,int field, int amount){
    Calendar now = Calendar.getInstance();
    SimpleDateFormat sf = new SimpleDateFormat(pattern);
    //可以是天数或月数  数字自定 -6前6个月
    now.add(field, amount);
    return sf.format(now.getTime());
  }

  public static void main(String[] args) {
    System.out.println(calculateNow("yyyyMMdd",Calendar.MONTH,-6));
  }

}
