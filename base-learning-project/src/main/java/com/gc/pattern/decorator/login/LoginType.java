package com.gc.pattern.decorator.login;

import com.gc.pattern.decorator.login.type.LoginTypeQQ;

/**
 * 登录策略
 * @author gaochao
 * @create 2020-10-09 23:22
 */
public interface LoginType {

  String LOGIN_TYPE_QQ = "QQ";
  String LOGIN_TYPE_WX = "WX";
  String LOGIN_TYPE_SINA = "SINA";

  String login(String id);

}
