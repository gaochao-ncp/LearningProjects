package com.gc.jdk8.function;

import java.util.function.Function;

/**
 * 函数式接口 {@link Function}
 * @author gaochao
 * @create 2020-07-31 15:24
 */
public class Test {

  public static void main(String[] args) {

    Function<Integer,Integer> f1 = x -> x - 10;

    Function<String,String> f2 = (x)-> "return " + x;

    Function<Integer,Integer> f3 = i -> i+1;

    System.out.println("f1.apply(1) = " + f1.apply(1));

    //先执行f3的apply再执行f1的apply方法
    System.out.println("f1.compose(f3).apply(5) = " + f1.compose(f3).apply(5));

    //先执行f1的apply方法再执行f3的apply方法 输出 //-8
    System.out.println("f1.andThen(f3) = " + f1.andThen(f3).apply(1));

  }

}
