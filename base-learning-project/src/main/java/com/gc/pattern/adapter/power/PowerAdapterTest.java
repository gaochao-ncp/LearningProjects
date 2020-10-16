package com.gc.pattern.adapter.power;

/**
 * 电源适配器测试类
 * @author gaochao
 * @create 2020-09-28 11:07
 */
public class PowerAdapterTest {

  public static void main(String[] args) {

    System.out.println("======对象适配器===========");
    PowerObjectAdapter powerObjectAdapter = new PowerObjectAdapter(new Ac220());
    powerObjectAdapter.outputDc5();

    System.out.println("======类适配器===========");
    PowerClassAdapter powerClassAdapter = new PowerClassAdapter();
    powerClassAdapter.outputDc5();
  }

}
