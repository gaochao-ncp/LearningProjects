package com.gc.pattern.delegate.simple;

/**
 * @author gaochao
 * @create 2020-09-26 22:46
 */
public class DelegateTest {

  public static void main(String[] args) {

    /**
     * 客户请求(Boss),委派者(Leader),被委派者(IEmployee)
     * 委派者要持有被委派者的引用,并且要知道每个被委派者的作用:通常是通过Map容器将被委派者缓存起来
     * 委派模式注重的是结果,代理模式注重的是过程;委派模式像代理模式中的静态代理模式的全权代理
     * 委派模式注重的是内部灵活和复用;策略模式注重的是外部拓展
     * 委派的核心是:分发,调度,派遣
     *
     *
     * 委派模式:静态代理和策略模式的一种特殊组合.
     */
    Boss.INSTANCE.doing("加密");
    Boss.INSTANCE.doing("业务");

  }

}
