package com.gc.pattern.delegate.simple;

/**
 * 员工A,特长加密
 * @author gaochao
 * @create 2020-09-26 22:33
 */
public class EmployeeA implements IEmployee {
  @Override
  public void doing(String command) {

    System.out.println("我是A员工,负责加密,正在执行{"+command+"}命令");

  }
}
