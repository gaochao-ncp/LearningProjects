package com.gc.pattern.delegate.simple;

/**
 * 员工C,特长业务
 * @author gaochao
 * @create 2020-09-26 22:33
 */
public class EmployeeC implements IEmployee {
  @Override
  public void doing(String command) {

    System.out.println("我是C员工,负责业务,正在执行{"+command+"}命令");

  }
}
