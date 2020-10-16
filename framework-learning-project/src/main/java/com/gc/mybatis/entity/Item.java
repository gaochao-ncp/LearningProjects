package com.gc.mybatis.entity;

import cn.hutool.core.util.RandomUtil;
import lombok.Data;

/**
 * @author gaochao
 * @create 2020-09-15 22:37
 */
@Data
public class Item {
  private int id;
  private String name;

  public static Item init(){
    Item i = new Item();
    i.setId(RandomUtil.randomInt(1));
    i.setName(RandomUtil.randomString(3));
    return i;

  }
}
