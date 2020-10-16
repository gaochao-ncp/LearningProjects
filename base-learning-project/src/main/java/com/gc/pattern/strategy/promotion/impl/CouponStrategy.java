package com.gc.pattern.strategy.promotion.impl;

import com.gc.pattern.strategy.promotion.PromotionStrategy;

/**
 * @author gaochao
 * @create 2020-09-26 23:23
 */
public class CouponStrategy implements PromotionStrategy {
  @Override
  public void doPromotion() {
    System.out.println("优惠券抵扣策略");
  }
}
