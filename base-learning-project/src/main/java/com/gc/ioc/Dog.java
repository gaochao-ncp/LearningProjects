package com.gc.ioc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author gaochao
 * @create 2020-04-15 15:12
 */
@Component
@Slf4j
public class Dog implements InitializingBean, DisposableBean {

  public Dog(){
    log.info("Dog 构造方法...");
  }

  @Override
  public void destroy() throws Exception {
    log.info("Dog destroy...");
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    log.info("Dog init...");
  }
}
