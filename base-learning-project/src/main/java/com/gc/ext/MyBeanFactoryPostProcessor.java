package com.gc.ext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author gaochao
 * @create 2020-05-21 16:39
 */
@Configuration
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    //获取定义的bean的个数
    int count = beanFactory.getBeanDefinitionCount();
    //获取定义的bean的名称
    String[] names = beanFactory.getBeanDefinitionNames();
    System.out.println("count = " + count);
    System.out.println("names = " + Arrays.toString(names));
  }
}
