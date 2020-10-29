package com.gc.spring.simple.controller;

import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import com.gc.spring.simple.annotation.GcAutowired;
import com.gc.spring.simple.annotation.GcController;
import com.gc.spring.simple.annotation.GcRequestMapping;
import com.gc.spring.simple.annotation.GcRequestParam;
import com.gc.spring.simple.service.IGcService;

/**
 * @author: Administrator
 * @date: 2020-10-26 16:55
 * @version: 1.0
 */
@GcController
@GcRequestMapping("/test")
public class ControllerTest {

  @GcAutowired
  private IGcService gcService;

  @GcRequestMapping("/query")
  public void query(HttpServerResponse res, HttpServerRequest req, @GcRequestParam String name){
    try {
      String rs = gcService.getName(name);
      res.getWriter().write(rs);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}
