package com.gc.pattern.decorator.login;

/**
 * @author gaochao
 * @create 2020-09-30 17:00
 */
public class LoginServiceImpl implements LoginService {

  @Override
  public String login(String id) {
    return "用户:"+id+"登录成功";
  }

  @Override
  public String register(String id) {
    return "用户:"+id+"注册成功";
  }
}
