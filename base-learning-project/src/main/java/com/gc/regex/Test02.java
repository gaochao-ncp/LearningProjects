package com.gc.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 贪婪模式和非贪婪模式
 * @author gaochao
 * @create 2020-08-04 0:28
 */
public class Test02 {

  public static void main(String[] args) {

    System.out.println("==============贪婪模式===================");

    String regex = "\\d{3,6}";
    String test = "61762828 176 2991 871";
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(test);
    while (m.find()){
      System.out.println("贪婪模式匹配结果："+m.group());
    }

    System.out.println("=================================");

    String regex1 = "(\\d{1,2})(\\d{3,4})";
    Pattern p1 = Pattern.compile(regex1);
    String test1="61762828 176 2991 87321";
    Matcher m1 = p1.matcher(test1);
    while (m1.find()){
      System.out.println("贪婪模式匹配结果："+m1.group());
    }

    System.out.println("==============非贪婪模式===================");


    String regex3="(\\d{1,2}?)(\\d{3,4}?)";
    String test3="61762828 176 2991 87321";
    Pattern p3 =Pattern.compile(regex3);
    Matcher m3 = p3.matcher(test3);
    while(m3.find()){
        System.out.println("非贪婪模式匹配结果："+m3.group());
     }

  }
}
