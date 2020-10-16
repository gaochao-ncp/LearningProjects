package com.gc.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则分组联系
 * @author gaochao
 * @create 2020-08-02 16:55
 */
public class GroupTest {

  public static void main(String[] args) {

    String test = "020-85653333";
    String regex = "(0\\d{2})-(\\d{8})";
    Pattern p = Pattern.compile(regex);

    Matcher mc = p.matcher(test);

    //从字符串任意位置开始匹配
    if (mc.find()){
      System.out.println("分组总数为 = " + mc.groupCount());
      for (int i = 0 ;i <= mc.groupCount();i++){
        System.out.println("第"+i+"个分组匹配到的内容为 = " + mc.group(i));
      }
    }


    String t1 = "aabbbbgbddesddfiid";
    String regex1 = "([a-z]|[A-Z])\\1";

    Pattern p2 = Pattern.compile(regex1);

    Matcher m1 = p2.matcher(t1);

    while (m1.find()){
      System.out.println("匹配到的全部内容为 =  " + m1.group())                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         ;
    }


  }
}
