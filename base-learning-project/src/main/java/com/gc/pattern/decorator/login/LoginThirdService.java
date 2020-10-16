package com.gc.pattern.decorator.login;

/**
 * @author gaochao
 * @create 2020-09-30 17:01
 */
public interface LoginThirdService extends LoginService {

  String loginByQq(String id);

  String loginBySina(String id);

  String loginByWechat(String id);

  String loginByStrategy(String id,String type);
}
