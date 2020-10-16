package com.gc.pattern.strategy.pay;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 订单类
 * @author gaochao
 * @create 2020-09-27 0:37
 */
@Data
@AllArgsConstructor
public class Order {

  private String id;
  private double price;


  public PayState pay(String key){
    System.out.println("正在处理的订单为{"+id+"}");
    Payment payment = PaymentFactory.getPayment(key);
    System.out.println(payment.toString());
    return payment.pay(price);
  }

}
