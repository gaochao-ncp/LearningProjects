package com.gc.pattern.strategy.pay;

import com.gc.pattern.strategy.pay.impl.AliPay;
import com.gc.pattern.strategy.pay.impl.JDPay;
import com.gc.pattern.strategy.pay.impl.WeChatPay;
import com.gc.pattern.strategy.promotion.PromotionStrategy;
import com.gc.pattern.strategy.promotion.PromotionStrategyFactory;
import com.gc.pattern.strategy.promotion.impl.CashbackStrategy;
import com.gc.pattern.strategy.promotion.impl.CouponStrategy;
import com.gc.pattern.strategy.promotion.impl.EmptyStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付策略工厂
 * @author gaochao
 * @create 2020-09-27 0:29
 */
public class PaymentFactory {

  private static final Map<String, Payment> PAYMENT_MAP = new HashMap<>();

  private static final Payment DEFAULT_PAYMENT = new AliPay();

  static {
    PAYMENT_MAP.put(PayKey.ALI_PAY,new AliPay());
    PAYMENT_MAP.put(PayKey.WECHAT_PAY,new WeChatPay());
    PAYMENT_MAP.put(PayKey.JD_PAY,new JDPay());
  }

  /**
   * 获取对应的支付策略
   * @param key
   * @return
   */
  public static Payment getPayment(String key){
    return PAYMENT_MAP.get(key) == null ? DEFAULT_PAYMENT : PAYMENT_MAP.get(key);
  }

  /**
   * 支付策略的key
   */
  public interface PayKey{
    String ALI_PAY="ALI_PAY";
    String WECHAT_PAY="WECHAT_PAY";
    String JD_PAY="JD_PAY";
  }
}
