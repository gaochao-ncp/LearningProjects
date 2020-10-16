package com.gc.pattern.adapter.sign;

/**
 * 登录策略:提供三种方式进行登录
 * 1.qq
 * 2.wechat
 * 3.sina
 * @author gaochao
 * @create 2020-09-28 19:49
 */
public interface SignStrategy {

  /**
   * 是否支持登录
   * @param loginAdapter
   * @return
   */
  boolean support(Object loginAdapter);

  /**
   * 登录算法
   * @param id
   * @param adapter
   * @return
   */
  SignResult login(String id,Object adapter);

}
