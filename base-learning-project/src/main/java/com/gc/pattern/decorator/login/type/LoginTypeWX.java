package com.gc.pattern.decorator.login.type;

import com.gc.pattern.decorator.login.LoginType;

/**
 * @author gaochao
 * @create 2020-10-09 23:25
 */
public class LoginTypeWX implements LoginType {
  @Override
  public String login(String id) {
    return "用户:"+id+"WX第三方登录成功";
  }
}
