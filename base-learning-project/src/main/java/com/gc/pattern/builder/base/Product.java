package com.gc.pattern.builder.base;

import lombok.Data;

/**
 * 产品角色：包含多个组成部件的复杂对象。
 *
 * @author: Administrator
 * @date: 2020-10-16 14:37
 * @version: 1.0
 */
@Data
public class Product {

  private String part_a;
  private String part_b;
  private String part_c;

}
