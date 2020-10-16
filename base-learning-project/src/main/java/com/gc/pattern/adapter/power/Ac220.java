package com.gc.pattern.adapter.power;

/**
 * 交流电220V接口:适配对象,适配者
 * @author gaochao
 * @create 2020-09-28 10:04
 */
public class Ac220 {

  public int outputAc220(){
    System.out.println("交流电220V接口输出为220V");
    return 220;
  }

}
