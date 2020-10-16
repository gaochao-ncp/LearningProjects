package com.gc.pattern.strategy.pay;

/**
 * 支付策略测试类
 * @author gaochao
 * @create 2020-09-27 0:40
 */
public class PaymentTest {

  public static void main(String[] args) {

    Order order_1 = new Order("电脑订单",333);
    PayState pay = order_1.pay(PaymentFactory.PayKey.JD_PAY);
    System.out.println(pay.toString());


    Order order_2 = new Order("购物订单",22);
    PayState pay_2 = order_2.pay(PaymentFactory.PayKey.JD_PAY);
    System.out.println(pay_2.toString());


  }

}
