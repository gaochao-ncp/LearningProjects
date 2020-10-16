package com.gc.generics;

import lombok.Data;

/**
 * @author gaochao
 * @create 2020-07-30 20:25
 */

@Data
public class Fruit {
  private String name = "水果";
}

@Data
class Apple extends Fruit{
  private String name = "苹果";

}

@Data
class Banana extends Fruit{
  private String name = "香蕉";
}
