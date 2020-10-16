package com.gc.pattern.observer.jdkgperadvice;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 需要发布的问题
 * @author gaochao
 * @create 2020-10-09 10:58
 */
@Data
@AllArgsConstructor
public class Question {

  private String userName;
  private String context;

}
