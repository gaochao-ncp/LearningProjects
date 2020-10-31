package com.gc.spring.simple.controller;


import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import com.gc.spring.simple.annotation.GcAutowired;
import com.gc.spring.simple.annotation.GcController;
import com.gc.spring.simple.annotation.GcRequestMapping;
import com.gc.spring.simple.annotation.GcRequestParam;
import com.gc.spring.simple.service.IGcService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
  public void query(HttpServletRequest res, HttpServletResponse req, @GcRequestParam("name") String name){
    try {
      String rs = gcService.getName(name);
      req.getWriter().write(rs);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}
