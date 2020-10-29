package com.gc.spring.simple.service.impl;

import com.gc.spring.simple.annotation.GcService;
import com.gc.spring.simple.service.IGcService;

/**
 * @author: Administrator
 * @date: 2020-10-26 16:56
 * @version: 1.0
 */
@GcService
public class GcServiceImpl implements IGcService {
  @Override
  public String getName(String name) {
    return "My name is " + name;
  }
}
