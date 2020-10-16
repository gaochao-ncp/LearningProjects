package com.gc.ioc;

import lombok.extern.slf4j.Slf4j;

/**
 * @author gaochao
 * @create 2020-04-15 14:29
 */
@Slf4j
public class Car {

  public Car(){
    log.info("car 构造方法");

  }

  public void init(){
    log.info("car 初始化");
  }

  public void destroy(){
    log.info("car 销毁");
  }
}
