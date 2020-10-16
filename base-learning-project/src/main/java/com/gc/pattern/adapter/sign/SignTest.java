package com.gc.pattern.adapter.sign;

/**
 * @author gaochao
 * @create 2020-09-28 11:58
 */
public class SignTest {

  public static void main(String[] args) {
    ThirdSignAdapter adapter = new ThirdSignAdapter();

    System.out.println(adapter.login("老用户", ""));

    System.out.println(adapter.loginForQQ("qq用户", ""));

    System.out.println(adapter.loginForSina("新浪用户", ""));

    System.out.println(adapter.loginForWechat("微信用户", ""));
//    SignResult<String> qq = third.loginForQQ("qq", "475855186");
//
//    System.out.println(qq);

  }

}
