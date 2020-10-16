package com.gc.pattern.adapter.sign;

/**
 * 第三方登录接口
 * @author gaochao
 * @create 2020-09-28 11:29
 */
public interface ThirdSignService {

  SignResult<String> loginForSina(String user,String pwd);

  SignResult<String> loginForWechat(String user,String pwd);

  SignResult<String> loginForQQ(String user,String pwd);

  SignResult<String> loginForRegister(String user,String pwd);

}
