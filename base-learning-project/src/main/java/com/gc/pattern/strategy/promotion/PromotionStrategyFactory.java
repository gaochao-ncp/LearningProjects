package com.gc.pattern.strategy.promotion;

import com.gc.pattern.strategy.promotion.impl.CashbackStrategy;
import com.gc.pattern.strategy.promotion.impl.CouponStrategy;
import com.gc.pattern.strategy.promotion.impl.EmptyStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 促销策略工厂
 * @author gaochao
 * @create 2020-09-26 23:44
 */
public class PromotionStrategyFactory {

  private static final Map<String,PromotionStrategy> PROMOTION_STRATEGY_MAP = new HashMap<>();

  private static final PromotionStrategy DEFAULT_STRATEGY = new EmptyStrategy();

  static {
    PROMOTION_STRATEGY_MAP.put(Key.COUPON,new CouponStrategy());
    PROMOTION_STRATEGY_MAP.put(Key.CASHBACK,new CashbackStrategy());
  }

  /**
   * 获取对应的促销策略
   * @param key
   * @return
   */
  public static PromotionStrategy getPromotionStrategy(String key){
    return PROMOTION_STRATEGY_MAP.get(key) == null ? DEFAULT_STRATEGY : PROMOTION_STRATEGY_MAP.get(key);
  }

  /**
   * 促销策略的key
   */
  public interface Key{
    String COUPON="COUPON";
    String CASHBACK="CASHBACK";
  }

}
