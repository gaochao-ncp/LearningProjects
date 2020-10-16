package com.gc.jdk8.function;

import java.util.function.Consumer;

/**
 * @author gaochao
 * @create 2020-09-17 16:08
 */
public class ConsumerTest {

  public static void main(String[] args) {
    Consumer<Integer> consumer = (x) -> {
      int num = x * 2;
      System.out.println(num);
    };
    Consumer<Integer> consumer1 = (x) -> {
      int num = x * 3;
      System.out.println(num);
    };
    /**
     * Consumer接口的默认方法andThen
     * 作用:需要两个Consumer接口,可以把两个Consumer接口组合到一起,在对数据进行消费
     * 谁写前面谁先消费
     */
    consumer.andThen(consumer1).accept(10);
  }
}
