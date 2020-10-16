package com.gc.pattern.strategy.pay.impl;

import com.gc.pattern.strategy.pay.Payment;

/**
 * @author gaochao
 * @create 2020-09-27 0:28
 */
public class JDPay extends Payment {
  @Override
  public String getName() {
    return "京东白条";
  }

  @Override
  public double queryBalance(String id) {
    return 111;
  }
}
