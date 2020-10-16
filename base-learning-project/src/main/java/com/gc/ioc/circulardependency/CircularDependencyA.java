package com.gc.ioc.circulardependency;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 循环依赖的例子A
 * @author gaochao
 * @create 2020-06-08 10:41
 */
@Component
@Slf4j
public class CircularDependencyA {

  @Autowired//由AutowiredAnnotationBeanPostProcessor自动注入
  private CircularDependencyB circularDependencyB;
  @Resource //由CommonAnnotationBeanPostProcessor自动注入
  private CircularDependencyC circularDependencyC;

  public CircularDependencyA(){
    log.info("CircularDependencyA 执行构造方法");
  }

  public void getB(){
    log.info("getB :: circularDependencyB ==> {}",circularDependencyB);
  }

  public void getC(){
    log.info("getC :: circularDependencyC ==> {}",circularDependencyC);
  }
}
