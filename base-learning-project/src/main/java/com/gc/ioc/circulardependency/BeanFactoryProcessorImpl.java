package com.gc.ioc.circulardependency;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;

/**
 * @author gaochao
 * @create 2020-06-09 11:35
 */
//@Component
public class BeanFactoryProcessorImpl implements BeanFactoryPostProcessor {
  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    // 将A类替换成B类
    //GenericBeanDefinition beanDefinition = (GenericBeanDefinition) beanFactory.getBeanDefinition("circularDependencyA");
    //beanDefinition.setBeanClass(CircularDependencyB.class);

    //关闭循环依赖
    AbstractAutowireCapableBeanFactory beanFactory1 = (AbstractAutowireCapableBeanFactory) beanFactory;
    beanFactory1.setAllowCircularReferences(Boolean.FALSE);

  }
}
