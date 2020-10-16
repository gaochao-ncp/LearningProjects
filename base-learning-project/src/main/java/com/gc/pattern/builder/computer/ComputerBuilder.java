package com.gc.pattern.builder.computer;

/**
 * 电脑组件生产抽象建造者
 *
 * @author: Administrator
 * @date: 2020-10-16 16:26
 * @version: 1.0
 */
public abstract class ComputerBuilder {

  protected Computer computer = new Computer();

  protected abstract void buildCpu();
  protected abstract void buildDisk();
  protected abstract void buildOs();

  public Computer getComputer(){
    buildCpu();
    buildDisk();
    buildOs();
    return computer;
  }

}
