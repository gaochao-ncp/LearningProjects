package com.gc.pattern.chain.demo;

/**
 * 责任链模式:抽象一个责任对象（当然可以是接口）,之所以要是抽象类或者接口,是为了让业务流转
 * @author gaochao
 * @create 2020-06-01 9:47
 */
public interface IResponsibility {

  /**
   * 处理逻辑的方法
   * @param input
   * @param iResponsibility
   */
  void doSomething(String input,IResponsibility iResponsibility);

}
