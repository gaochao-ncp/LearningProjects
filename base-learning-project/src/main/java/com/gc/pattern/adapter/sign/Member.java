package com.gc.pattern.adapter.sign;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 会员类
 * @author gaochao
 * @create 2020-09-28 11:24
 */
@Data
@Accessors(chain = true)
public class Member {
  private String mid;
  private String user;
  private String pwd;
  private String info;
}
