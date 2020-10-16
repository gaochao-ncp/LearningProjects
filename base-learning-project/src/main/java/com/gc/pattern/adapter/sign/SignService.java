package com.gc.pattern.adapter.sign;

/**
 * 老的登录系统的逻辑
 * @author gaochao
 * @create 2020-09-28 11:19
 */
public class SignService {

  /**
   * 注册方法
   * @param user
   * @param pwd
   * @return
   */
  public SignResult<Member> register(String user, String pwd){
    return new SignResult<Member>(200,"注册用户{"+user+"},密码为{"+pwd+"}成功",new Member().setUser(user).setPwd(pwd));
  }

  /**
   * 登录方法
   * @param user
   * @param pwd
   * @return
   */
  public SignResult<String> login(String user, String pwd){
    return new SignResult<String>(200,"老方法登录成功","用户:"+user+"登录成功!");
  }

}
