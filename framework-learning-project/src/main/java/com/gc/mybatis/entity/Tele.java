package com.gc.mybatis.entity;

import cn.hutool.core.util.RandomUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gaochao
 * @create 2020-09-15 22:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tele{

  private String country;
  private String type;
  private String num;

  public static Tele init(){
    return new Tele(RandomUtil.randomString(3),RandomUtil.randomString(2),RandomUtil.randomString(1));
  }

}
