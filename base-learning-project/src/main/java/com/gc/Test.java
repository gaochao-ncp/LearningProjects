package com.gc;

import com.gc.pattern.singleton.ConcurrentExecutor;
import lombok.Data;

import javax.servlet.http.HttpServlet;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author: Administrator
 * @date: 2020-10-22 11:48
 * @version: 1.0
 */
@Data
public class Test extends HttpServlet {

  private String test;
  private String name;

  private static int sequence = 0;

  private static int length = 6;

  public static void main(String[] args) {

//    ConcurrentExecutor.execute(()->{
//      System.out.println(Test.getLocalTrmSeqNum());
//    },1000001,10000);
//    SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss");
//    System.out.println(sf.format(new Date()));
    System.out.println("Test.class.getSimpleName() = " + Test.class.getSimpleName());
    System.out.println(Test.class.getName());

    try {
      Field[] test = Test.class.getDeclaredFields();
      for (Field field : test) {
        System.out.println(field.getGenericType());
        System.out.println(field.getType().getName());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }


  }

  /**
   * YYYYMMDDHHMMSS+6位自增长码(20位)
   * @author shijing
   * 2015年6月29日下午1:25:23
   * @return
   */
  public static synchronized String getLocalTrmSeqNum() {
    sequence = sequence >= 999999 ? 1 : sequence + 1;
    String datetime = new SimpleDateFormat("MMdd").format(new Date());
    String s = Integer.toString(sequence);
    return datetime +""+addLeftZero(s, length);
  }

  /**
   * 左填0
   * @author shijing
   * 2015年6月29日下午1:24:32
   * @param s
   * @param length
   * @return
   */
  public static String addLeftZero(String s, int length) {
    // StringBuilder sb=new StringBuilder();
    int old = s.length();
    if (length > old) {
      char[] c = new char[length];
      char[] x = s.toCharArray();
      if (x.length > length) {
        throw new IllegalArgumentException(
                "Numeric value is larger than intended length: " + s
                        + " LEN " + length);
      }
      int lim = c.length - x.length;
      for (int i = 0; i < lim; i++) {
        c[i] = '0';
      }
      System.arraycopy(x, 0, c, lim, x.length);
      return new String(c);
    }
    return s.substring(0, length);
  }
}
