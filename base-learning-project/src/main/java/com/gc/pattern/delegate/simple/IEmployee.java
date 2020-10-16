package com.gc.pattern.delegate.simple;

/**
 * 员工抽象接口:被委派者
 * @author gaochao
 * @create 2020-09-26 22:32
 */
public interface IEmployee {

  /**
   * 执行命令
   * @param command
   */
  void doing(String command);

}
