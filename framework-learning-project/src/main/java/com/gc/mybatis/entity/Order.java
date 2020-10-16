package com.gc.mybatis.entity;

import cn.hutool.core.util.RandomUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaochao
 * @create 2020-09-15 22:38
 */
@Data
public class Order {
  private String id;
  private List<Item> items;

  public static Order init(int i){
    Order o = new Order();
    o.setId(RandomUtil.randomString(1));
    List<Item> items = new ArrayList<>();
    while (i>0){
      items.add(Item.init());
      i--;
    }
    return o;
  }
}
