package com.gc.pattern.decorator.login;

/**
 * @author gaochao
 * @create 2020-09-30 17:05
 */
public class LoginTest {

  public static void main(String[] args) {

    LoginThirdService thirdService = new LoginThirdServiceImpl(new LoginServiceImpl());
    System.out.println(thirdService.login("zzzz"));
    System.out.println(thirdService.loginBySina("xadafafsa"));
  }

}
