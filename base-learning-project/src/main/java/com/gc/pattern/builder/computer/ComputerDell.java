package com.gc.pattern.builder.computer;

/**
 * 戴尔电脑
 *
 * @author: Administrator
 * @date: 2020-10-16 16:29
 * @version: 1.0
 */
public class ComputerDell extends ComputerBuilder {
  @Override
  public void buildCpu() {
    super.computer.setCpu("戴尔cpu");
  }

  @Override
  public void buildDisk() {
    super.computer.setDisk("戴尔内存");
  }

  @Override
  public void buildOs() {
    super.computer.setOs("戴尔os");
  }
}
