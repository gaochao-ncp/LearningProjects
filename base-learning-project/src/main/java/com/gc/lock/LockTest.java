package com.gc.lock;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

public class LockTest {

  boolean l = Boolean.FALSE;//1字节
  int i = 0 ; //4字节

  public static void main(String[] args) {
    LockTest obj = new LockTest();
    //查看对象内部信息： ClassLayout.parseInstance(obj).toPrintable()
    System.out.println("查看对象内部信息=====================");
    System.out.println(ClassLayout.parseInstance(obj).toPrintable());

    //查看对象外部信息：包括引用的对象：GraphLayout.parseInstance(obj).toPrintable()
    System.out.println("查看对象外部信息=====================");
    System.out.println(GraphLayout.parseInstance(obj).toPrintable());

    //查看对象占用空间总大小：GraphLayout.parseInstance(obj).totalSize()
    System.out.println("查看对象占用空间总大小=====================");
    System.out.println(GraphLayout.parseInstance(obj).totalSize());

    //对象补齐 ：字节大小要是8的倍数

  }


}
