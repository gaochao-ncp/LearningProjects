package com.gc.ioc.circulardependency;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 循环依赖的例子C
 * @author gaochao
 * @create 2020-06-08 10:41
 */
@Component
@Slf4j
public class CircularDependencyC {

  public CircularDependencyC(){
    log.info("CircularDependencyC 执行构造方法");
  }

}
