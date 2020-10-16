package com.gc.ioc.circulardependency;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 循环依赖的例子B
 * @author gaochao
 * @create 2020-06-08 10:41
 */
@Component
@Slf4j
public class CircularDependencyB {

  @Autowired
  private CircularDependencyA circularDependencyA;

  public CircularDependencyB(){
    log.info("CircularDependencyB 执行构造方法");
  }

  public void getA(){
    log.info("getA :: circularDependencyA ==> {}",circularDependencyA);
  }
}
