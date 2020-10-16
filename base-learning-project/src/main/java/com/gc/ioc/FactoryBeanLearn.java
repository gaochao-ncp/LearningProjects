package com.gc.ioc;

import lombok.Data;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * 工厂bean，用来做ioc的测试
 * @author gaochao
 * @create 2020-06-03 9:59
 */
@Component
@Data
public class FactoryBeanLearn implements FactoryBean {

  private String msg ;

  public FactoryBeanLearn(){
    this.msg="通过构造方法初始化FactoryBeanLearn";
  }

  @Override
  public Object getObject() throws Exception {
    FactoryBeanLearn learn = new FactoryBeanLearn();
    learn.setMsg("通过FactoryBean 初始化");
    // 这里并不一定要返回MyBean自身的实例，可以是其他任何对象的实例
    return learn;
  }

  @Override
  public Class<?> getObjectType() {
    return FactoryBeanLearn.class;
  }
}
