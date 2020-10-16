package com.gc.pattern.delegate.simple;

/**
 * 员工B,特长销售
 * @author gaochao
 * @create 2020-09-26 22:33
 */
public class EmployeeB implements IEmployee {
  @Override
  public void doing(String command) {

    System.out.println("我是B员工,负责销售,正在执行{"+command+"}命令");

  }
}
