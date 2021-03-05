package com.gc.pattern.chain.handler;

/**
 * Description:
 * 抽象处理者
 * @author gaochao
 * @date 2020-10-16 11:02
 */
public abstract class Handler {

  private Handler next;

  public static Handler getHandler(){
    Handler h1 = new ConcreteHandler1();
    Handler h2 = new ConcreteHandler2();
    h1.setNext(h2);
    return h1;
  }

  public void setNext(Handler next){
    this.next=next;
  }

  public Handler getNext(){
    return this.next;
  }

  /**
   * 处理请求
   * @param request
   */
  protected abstract void handlerRequest(String request);
}
