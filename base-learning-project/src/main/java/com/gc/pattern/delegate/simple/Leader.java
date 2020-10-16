package com.gc.pattern.delegate.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * 经理类:委派者,将任务下发.
 * 作为中间的代理层,应该明确知道每个员工的特性是什么,所以需要将员工缓存起来
 * @author gaochao
 * @create 2020-09-26 22:32
 */
public class Leader {

  private static final Map<String,IEmployee> EMPLOYEE_MAP = new HashMap<>();

  static {
    EMPLOYEE_MAP.put("加密",new EmployeeA());
    EMPLOYEE_MAP.put("业务",new EmployeeB());
    EMPLOYEE_MAP.put("销售",new EmployeeC());
  }

  public static void doEmployee(String command){
    if (!EMPLOYEE_MAP.containsKey(command)){
      System.out.println("没有相应的员工的处理{"+command+"}命令");
    }
    EMPLOYEE_MAP.get(command).doing(command);
  }

}
