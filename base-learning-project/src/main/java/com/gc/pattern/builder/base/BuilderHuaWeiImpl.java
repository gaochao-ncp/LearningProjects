package com.gc.pattern.builder.base;

/**
 * 具体建造者
 *
 * @author: Administrator
 * @date: 2020-10-16 14:42
 * @version: 1.0
 */
public class BuilderHuaWeiImpl implements IBuilder {

  @Override
  public String builderPartA(String partA) {
    return "华为零件A:"+partA;
  }

  @Override
  public String builderPartB(String partB) {
    return "华为零件B:"+partB;
  }

  @Override
  public String builderPartC(String partC) {
    return "华为零件C:"+partC;
  }
}
