package com.gc.ioc.di;

/**
 * @author gaochao
 * @create 2020-08-13 15:53
 */
@org.springframework.stereotype.Service("serviceImpl2")
public class ServiceImpl2 implements Service {
  @Override
  public String getName() {
    return "ServiceImpl-2";
  }
}
