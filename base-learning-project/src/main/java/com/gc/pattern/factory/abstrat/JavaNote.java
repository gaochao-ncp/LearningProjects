package com.gc.pattern.factory.abstrat;

/**
 * @author gaochao
 * @create 2020-09-20 0:20
 */
public class JavaNote implements INote {

  @Override
  public INote creatNote() {
    System.out.println("java 笔记");
    return new JavaNote();
  }
}
