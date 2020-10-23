package com.gc.delaytask;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Administrator
 * @date: 2020-10-20 16:25
 * @version: 1.0
 */
public class NYConfig {

  /**轮询机制 单位为min**/
  public static Map<Integer,Integer> roundTime = new HashMap<>(5);

  static {
    roundTime.put(1,0);
    roundTime.put(2,10);
    roundTime.put(3,10);
    roundTime.put(4,10);
    roundTime.put(5,10);
  }

  /**获取最大重发次数**/
  public static Integer getMaxLimitTimes() {
    return roundTime.size();
  }
}
