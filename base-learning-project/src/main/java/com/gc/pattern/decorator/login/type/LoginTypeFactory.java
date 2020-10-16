package com.gc.pattern.decorator.login.type;

import com.gc.pattern.decorator.login.LoginType;

/**
 * 登录策略工厂
 * @author gaochao
 * @create 2020-10-09 23:39
 */
public class LoginTypeFactory {

  public static LoginType getInstance(String type){
    if (LoginType.LOGIN_TYPE_QQ.equals(type)){
      return new LoginTypeQQ();
    }else if (LoginType.LOGIN_TYPE_SINA.equals(type)){
      return new LoginTypeSina();
    }else if (LoginType.LOGIN_TYPE_WX.equals(type)){
      return new LoginTypeWX();
    }else {
      throw new RuntimeException("没有找到对应的登录策略,请重试");
    }
  }

}
