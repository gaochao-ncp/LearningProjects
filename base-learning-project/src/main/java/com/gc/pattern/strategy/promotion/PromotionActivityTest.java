package com.gc.pattern.strategy.promotion;

/**
 * 客户端测试类
 * @author gaochao
 * @create 2020-09-26 23:27
 */
public class PromotionActivityTest {

    //在实际代码中并不会这样调用策略,因为如果策略增多,会增加代码的改动,所以我们这里利用工厂模式进行优化
    //其实我觉得工厂模式其实很像委派模式,促销活动类是委派者,促销策略是被委派者,负责执行不同的促销策略,不知道我这样理解有没有偏差
//  public static void main(String[] args) {
//    PromotionActivity activity_618 = new PromotionActivity(new CashbackStrategy());
//    PromotionActivity activity_1111 = new PromotionActivity(new CouponStrategy());
//
//    activity_618.executePromotion();
//    activity_1111.executePromotion();
//  }

  //通过工厂模式优化了代码,每次上新的策略易于维护,并且不影响原来的逻辑
  public static void main(String[] args) {
    PromotionActivity activity = new PromotionActivity();
    activity.executePromotion(PromotionStrategyFactory.Key.COUPON);
    activity.executePromotion("");
  }


}
