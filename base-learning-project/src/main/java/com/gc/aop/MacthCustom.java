package com.gc.aop;

import org.springframework.stereotype.Component;

/**
 * @author gaochao
 * @create 2020-04-15 20:49
 */
@Component
public class MacthCustom {

  public int match(int x,int y){
    return x/y;
  }

}
