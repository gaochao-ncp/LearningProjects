package com.gc.pattern.adapter.sign.strategy;

import com.gc.pattern.adapter.sign.SignResult;
import com.gc.pattern.adapter.sign.SignStrategy;

/**
 * qq登录策略
 * @author gaochao
 * @create 2020-09-28 19:58
 */
public class SignStrategyForQQ implements SignStrategy {
  @Override
  public boolean support(Object loginAdapter) {
    return false;
  }

  @Override
  public SignResult login(String id, Object adapter) {
    return new SignResult(200,"QQ登录成功","账号:"+id+"登录成功");
  }
}
