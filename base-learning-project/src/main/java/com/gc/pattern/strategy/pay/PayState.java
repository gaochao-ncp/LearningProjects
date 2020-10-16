package com.gc.pattern.strategy.pay;

import lombok.Data;

/**
 * 支付状态:支付后的返回结果
 * @author gaochao
 * @create 2020-09-27 0:20
 */
@Data
public class PayState {
  @Override
  public String toString() {
    return "支付状态为{'" + code + '\'' +
            "}, 支付返回信息{'" + msg + '\'' +
            '}';
  }

  private String code;
  private String msg;

  public static PayState getPayState(String code,String msg){
    PayState payState = new PayState();
    payState.setCode(code);
    payState.setMsg(msg);
    return payState;
  }
}
