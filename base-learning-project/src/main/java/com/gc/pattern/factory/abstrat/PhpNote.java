package com.gc.pattern.factory.abstrat;

/**
 * @author gaochao
 * @create 2020-09-20 0:20
 */
public class PhpNote implements INote {

  @Override
  public INote creatNote() {
    System.out.println("php 笔记");
    return new JavaNote();
  }
}
