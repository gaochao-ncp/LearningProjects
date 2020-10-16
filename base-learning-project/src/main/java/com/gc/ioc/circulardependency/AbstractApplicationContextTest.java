package com.gc.ioc.circulardependency;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author gaochao
 * @create 2020-06-09 16:37
 */
@Component
public class AbstractApplicationContextTest extends AbstractApplicationContext {

  @Override
  protected void refreshBeanFactory() throws BeansException, IllegalStateException {

  }

  @Override
  protected void closeBeanFactory() {

  }

  @Override
  public ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException {
    return null;
  }

  @Override
  protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
    AbstractAutowireCapableBeanFactory beanFactory1 = (AbstractAutowireCapableBeanFactory) beanFactory;
    beanFactory1.setAllowCircularReferences(Boolean.FALSE);
  }
}
