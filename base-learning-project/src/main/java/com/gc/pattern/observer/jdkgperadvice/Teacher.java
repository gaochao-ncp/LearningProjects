package com.gc.pattern.observer.jdkgperadvice;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Observable;
import java.util.Observer;

/**
 * @author gaochao
 * @create 2020-10-09 11:03
 */
@Data
@AllArgsConstructor
public class Teacher implements Observer {

  private String teacherName;

  @Override
  public void update(Observable o, Object arg) {
    Gper gper = (Gper) o;
    Question question = (Question) arg;
    System.out.println("===============================");
    System.out.println(this.teacherName+"老师已经收到来自"+gper.getName()+" "+question.getUserName()+"同学提出的问题:"+question.getContext()+"?");
  }
}
