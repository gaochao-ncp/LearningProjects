package com.gc.pattern.chain.handler;

/**
 * Description: 具体处理者1
 * @author gaochao
 * @date 2020-10-16 11:05
 */
public class ConcreteHandler1 extends Handler {

  @Override
  protected void handlerRequest(String request) {
    if ("one".equals(request)){
      System.out.println("ConcreteHandler1进行处理");
    }else {
      if (getNext() != null){
        getNext().handlerRequest(request);
      }else {
        System.out.println("没有对应的请求链...");
      }
    }
  }

}
