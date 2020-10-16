package com.gc.pattern.observer.jdkgperadvice;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Observable;

/**
 * jdk提供的一种观察者的实现
 * 被观察者
 * @author gaochao
 * @create 2020-10-09 10:32
 */
@Data
public class Gper extends Observable {

  private String name = "Gper生态圈";

  public void publishQuestion(Question question){
    System.out.println(question.getUserName()+"同学在"+this.name+"发布了一个问题:"+question.getContext());
    setChanged();
    notifyObservers(question);
  }

}
