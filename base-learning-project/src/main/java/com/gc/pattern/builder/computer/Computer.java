package com.gc.pattern.builder.computer;

import lombok.Data;

/**
 * 电脑:包含cpu,disk,os等组成部分
 *
 * @author: Administrator
 * @date: 2020-10-16 16:25
 * @version: 1.0
 */
@Data
public class Computer {

  private String cpu;
  private String os;
  private String disk;

}
