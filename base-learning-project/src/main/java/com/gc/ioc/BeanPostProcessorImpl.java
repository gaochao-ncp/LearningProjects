package com.gc.ioc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author gaochao
 * @create 2020-04-15 15:46
 */
@Component
@Slf4j
public class BeanPostProcessorImpl implements BeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    log.info("BeanPostProcessorImpl postProcessBeforeInitialization"+beanName);
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    log.info("BeanPostProcessorImpl postProcessAfterInitialization"+beanName);
    return bean;
  }
}
