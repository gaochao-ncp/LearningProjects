package com.gc.pattern.adapter.sign;

import com.gc.pattern.adapter.sign.strategy.SignStrategyForQQ;
import com.gc.pattern.adapter.sign.strategy.SignStrategyForSina;
import com.gc.pattern.adapter.sign.strategy.SignStrategyForWeChat;

/**
 *
 * 第三方登录适配器
 * @author gaochao
 * @create 2020-09-28 20:07
 */
public class ThirdSignAdapter extends SignService implements ThirdSignService {


  @Override
  public SignResult<String> loginForSina(String user, String pwd) {
    return doSign(user,SignStrategyForSina.class);
  }

  @Override
  public SignResult<String> loginForWechat(String user, String pwd) {
    return doSign(user, SignStrategyForWeChat.class);
  }

  @Override
  public SignResult<String> loginForQQ(String user, String pwd) {
    return doSign(user, SignStrategyForQQ.class);
  }

  @Override
  public SignResult<String> loginForRegister(String user, String pwd) {
    return super.login(user, pwd);
  }

  /**
   * 简单工厂模式
   * @param user
   * @param clz
   * @return
   */
  private SignResult<String> doSign(String user,Class<? extends SignStrategy> clz){

    try {
      SignStrategy strategy = clz.newInstance();
      if (!strategy.support("")){
        //不支持当前方式登录的话返回错误信息
        return new SignResult<String>(500,"不支持当前方式登录","账号:"+user+"登录失败");
      }
      return strategy.login(user,"");

    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return new SignResult(200,"第三方登录成功","账号:"+user+"登录成功");
  }
}
