package com.gc.pattern.decorator.login;

import com.gc.pattern.decorator.login.type.LoginTypeFactory;

/**
 * @author gaochao
 * @create 2020-09-30 17:02
 */
public class LoginThirdServiceImpl implements LoginThirdService {

  private LoginService loginService;

  public LoginThirdServiceImpl(LoginService loginService){
    this.loginService=loginService;
  }

  @Override
  public String loginByQq(String id) {
    return "用户:"+id+"QQ第三方登录成功";
  }

  @Override
  public String loginBySina(String id) {
    return "用户:"+id+"Sina第三方登录成功";
  }

  @Override
  public String loginByWechat(String id) {
    return "用户:"+id+"WeChat第三方登录成功";
  }

  @Override
  public String loginByStrategy(String id, String type) {
    return LoginTypeFactory.getInstance(type).login(id);
  }

  @Override
  public String login(String id) {
    return loginService.login(id);
  }

  @Override
  public String register(String id) {
    return loginService.register(id);
  }
}
