package com.gc.pattern.delegate.simple;

/**
 * boss类,客户端,请求委派者{@link Leader}
 * @author gaochao
 * @create 2020-09-26 22:31
 */
public enum Boss {

  INSTANCE;

  /**
   * 将任务委派给leader经理进行处理
   * @param command
   */
  public void doing(String command){
    Leader.doEmployee(command);
  }


}
