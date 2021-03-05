package com.gc.pattern.chain.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * 责任链模式:将责任链各个点用链表的形式保存起来，形成一个完整的责任链模型。
 * @author gaochao
 * @create 2020-06-01 10:00
 */
public class ResponsibilityChain implements IResponsibility {

  /**
   * 责任链表
   */
  private List<IResponsibility> list = new ArrayList<>();

  /**
   * 索引,记录当前执行到第几个责任链
   */
  private int index = 0 ;

  /**
   * 链式添加责任链
   * @param responsibility
   * @return
   */
  public ResponsibilityChain addChain(IResponsibility responsibility){
    list.add(responsibility);
    return this;
  }

  @Override
  public void doSomething(String input, IResponsibility iResponsibility) {

    //chain执行完成,返回
    if (index == list.size()){
      return;
    }
    //获取当前iResponsibility对象
    IResponsibility currentIResponsibility = list.get(index);
    //修改索引值,方便下次回调获取节点,达到遍历效果
    index++;
    //调用当前责任对象处理方法
    currentIResponsibility.doSomething(input, this::doSomething);
  }

  public static void main(String[] args) {
    ResponsibilityChain chain = new ResponsibilityChain();
    chain.addChain(new AResponsibility());
    chain.addChain(new BResponsibility());
    chain.addChain(new CResponsibility());

    chain.doSomething("C",chain);

  }
}
