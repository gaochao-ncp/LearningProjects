package com.gc.pattern.strategy.promotion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 促销活动类
 * @author gaochao
 * @create 2020-09-26 23:25
 */
@Data
public class PromotionActivity {

  /**
   * 促销策略,优化代码,直接通过促销策略工厂来创建对应的促销策略
   */
  //private PromotionStrategy promotionStrategy;

  /**
   * 执行对应的促销策略
   */
  public void executePromotion(String key){
    PromotionStrategyFactory.getPromotionStrategy(key).doPromotion();
  }

}
