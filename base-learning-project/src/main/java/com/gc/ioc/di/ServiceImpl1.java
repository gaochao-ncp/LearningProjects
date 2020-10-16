package com.gc.ioc.di;

/**
 * @author gaochao
 * @create 2020-08-13 15:53
 */
@org.springframework.stereotype.Service("serviceImpl1")
public class ServiceImpl1 implements Service {

  @Override
  public String getName() {
    return "ServiceImpl-1";
  }

}
