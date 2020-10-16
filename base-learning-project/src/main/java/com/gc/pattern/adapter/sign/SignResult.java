package com.gc.pattern.adapter.sign;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 封装登录后的返回消息
 * @author gaochao
 * @create 2020-09-28 11:18
 */
@Data
@AllArgsConstructor
public class SignResult<T> {
  private int code;
  private String msg;
  private T data;
}
