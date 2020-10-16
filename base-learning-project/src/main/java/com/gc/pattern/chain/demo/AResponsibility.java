package com.gc.pattern.chain.demo;

import lombok.extern.slf4j.Slf4j;

/**
 * 责任链模式:A责任点
 * @author gaochao
 * @create 2020-06-01 9:49
 */
@Slf4j
public class AResponsibility implements IResponsibility {
  @Override
  public void doSomething(String input, IResponsibility iResponsibility) {

    if ("A".equals(input)){
      //A责任点业务逻辑
      log.info("A Responsibility do something");
      return;
    }
    //当前没办法处理,让下一个责任点处理
    iResponsibility.doSomething(input, iResponsibility);
  }
}
