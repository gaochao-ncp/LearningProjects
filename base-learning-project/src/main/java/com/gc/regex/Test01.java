package com.gc.regex;

import java.util.regex.Pattern;

/**
 * @author gaochao
 * @create 2020-08-02 17:09
 */
public class Test01 {

  public static void main(String[] args) {
    //创建一个正则表达式实例
    String regex = "regex";
    Pattern p = Pattern.compile(regex);
    Pattern p2 = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);


    //匹配两个数字
    System.out.println("Pattern.matches() = " + Pattern.matches("\\d{2}","12"));;
    //匹配英文字符
    System.out.println("Pattern.matches() = " + Pattern.matches("([a-z]|[A-Z]){2}","ad"));;
  }

}
