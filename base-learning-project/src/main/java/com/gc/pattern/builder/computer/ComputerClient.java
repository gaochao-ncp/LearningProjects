package com.gc.pattern.builder.computer;

/**
 * 测试类
 *
 * @author: Administrator
 * @date: 2020-10-16 16:34
 * @version: 1.0
 */
public class ComputerClient {

  public static void main(String[] args) {

    ComputerBuilder mac = new ComputerMac();
    Computer macComputer = mac.getComputer();
    System.out.println(macComputer);


    ComputerBuilder dell = new ComputerDell();
    Computer dellComputer = dell.getComputer();
    System.out.println(dellComputer);

  }

}
