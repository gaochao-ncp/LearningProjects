package com.gc.pattern.builder.base;

/**
 * 抽象建造者:负责创建产品的各个组成(可以是接口也可以是抽象类,看自己的选择)
 *
 * @author: Administrator
 * @date: 2020-10-16 14:38
 * @version: 1.0
 */
public interface IBuilder {

  String builderPartA(String partA);

  String builderPartB(String partB);

  String builderPartC(String partC);

}
