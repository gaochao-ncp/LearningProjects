package com.gc.pattern.builder.base;

/**
 * 具体建造者
 *
 * @author: Administrator
 * @date: 2020-10-16 14:42
 * @version: 1.0
 */
public class BuilderTclImpl implements IBuilder {
  @Override
  public String builderPartA(String partA) {
    return "TCL零件A:"+partA;
  }

  @Override
  public String builderPartB(String partB) {
    return "TCL零件A:"+partB;
  }

  @Override
  public String builderPartC(String partC) {
    return "TCL零件A:"+partC;
  }
}
