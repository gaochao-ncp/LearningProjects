package com.gc.mybatis.entity;

import cn.hutool.core.util.RandomUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaochao
 * @create 2020-08-05 11:55
 */
@Data
public class User {

  private String id;
  private Tele tele;
  private List<Order> orders;

  public static User init(int o){
    User u = new User();
    u.setId(RandomUtil.randomString(1));
    u.setTele(Tele.init());
    List<Order> orders = new ArrayList<>();
    while (o>0){
      orders.add(Order.init(o));
      o--;
    }
    u.setOrders(orders);
    return u;
  }

}
