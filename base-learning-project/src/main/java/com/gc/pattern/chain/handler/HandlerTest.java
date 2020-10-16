package com.gc.pattern.chain.handler;

/**
 * 责任链测试类
 *
 * @version: 1.0
 * @author: Administrator
 * @date: 2020-10-16 11:20
 */
public class HandlerTest {

  public static void main(String[] args) {

    Handler h1 = new ConcreteHandler1();
    Handler h2 = new ConcreteHandler2();

    h1.setNext(h2);

    h1.handlerRequest("one");
    h1.handlerRequest("two");
    h1.handlerRequest("three");


  }

}
