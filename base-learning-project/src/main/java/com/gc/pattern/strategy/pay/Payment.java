package com.gc.pattern.strategy.pay;

/**
 * 支付渠道:支付的抽象类.
 * @author gaochao
 * @create 2020-09-27 0:08
 */
public abstract class Payment {

  /**
   * 获取支付名称
   * @return
   */
  public abstract String getName();

  /**
   * 查询余额
   * @param id
   * @return
   */
  public abstract double queryBalance(String id);

  public PayState pay(double price){
    if (queryBalance("")<price){
      return PayState.getPayState("500","余额不足");
    }
    return PayState.getPayState("200","支付成功");
  }


  @Override
  public String toString() {
    return "欢迎使用{"+getName()+"},当前账户余额为{"+queryBalance("")+"}";
  }
}
