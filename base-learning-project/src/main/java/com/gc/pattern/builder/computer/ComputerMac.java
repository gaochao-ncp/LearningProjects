package com.gc.pattern.builder.computer;

/**
 * 苹果电脑
 *
 * @author: Administrator
 * @date: 2020-10-16 16:29
 * @version: 1.0
 */
public class ComputerMac extends ComputerBuilder {
  @Override
  public void buildCpu() {
    super.computer.setCpu("苹果cpu");
  }

  @Override
  public void buildDisk() {
    super.computer.setDisk("苹果内存");
  }

  @Override
  public void buildOs() {
    super.computer.setOs("苹果os");
  }
}
