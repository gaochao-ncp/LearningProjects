package com.gc.pattern.chain.demo;

import lombok.extern.slf4j.Slf4j;

/**
 * 责任链模式:B责任点
 * @author gaochao
 * @create 2020-06-01 9:49
 */
@Slf4j
public class BResponsibility implements IResponsibility {
  @Override
  public void doSomething(String input, IResponsibility iResponsibility) {

    if ("B".equals(input)){
      //B责任点业务逻辑
      log.info("B Responsibility do something");
      return;
    }
    //当前没办法处理,让下一个责任点处理
    iResponsibility.doSomething(input, iResponsibility);
  }
}
