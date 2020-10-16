package com.gc.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author gaochao
 * @create 2020-04-15 14:29
 */
@Configuration
@ComponentScan("com.gc.ioc")
public class IocMainConfig {

  /**
   * 使用bean注解指定初始化和销毁方法
   * @return
   */
  //@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  @Bean(initMethod = "init",destroyMethod = "destroy",name = "gcCar")
  public Car car(){
    return new Car();
  }

  @Bean
  public BeanPostProcessorImpl beanPostProcessor(){
    return new BeanPostProcessorImpl();
  }

}
