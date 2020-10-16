package com.gc.pattern.adapter.power;

/**
 * 电流转换器
 * 对象适配器:适配器和适配者是关联关系
 * @author gaochao
 * @create 2020-09-28 10:59
 */
public class PowerObjectAdapter implements Dc5 {

  private Ac220 ac220;

  public PowerObjectAdapter(Ac220 ac220){
    this.ac220=ac220;
  }

  @Override
  public int outputDc5() {
    int inputAc = ac220.outputAc220();
    //变压器转换
    int outputAc = inputAc/44;
    System.out.println("使用电源适配器输入"+outputAc+"电压,输出"+inputAc+"电压");
    return outputAc;
  }


}
