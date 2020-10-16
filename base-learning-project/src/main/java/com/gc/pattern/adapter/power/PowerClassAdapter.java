package com.gc.pattern.adapter.power;

/**
 * 电流转换器:适配器
 * 类适配器:适配器和适配者是继承关系
 * @author gaochao
 * @create 2020-09-28 10:59
 */
public class PowerClassAdapter extends Ac220 implements Dc5 {

  @Override
  public int outputDc5() {
    int inputAc = super.outputAc220();
    //变压器转换
    int outputAc = inputAc/44;
    System.out.println("使用电源适配器输入"+outputAc+"电压,输出"+inputAc+"电压");
    return outputAc;
  }

}
