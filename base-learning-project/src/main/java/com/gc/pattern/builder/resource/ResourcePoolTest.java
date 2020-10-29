package com.gc.pattern.builder.resource;

/**
 * 测试类
 *
 * @author: Administrator
 * @date: 2020-10-22 15:26
 * @version: 1.0
 */
public class ResourcePoolTest {

  public static void main(String[] args) {
    ResourcePoolConfig_V1 v1 = new ResourcePoolConfig_V1("ResourcePoolConfig_V1",0,0,0);
    System.out.println(v1.toString());

    ResourcePoolConfig_V2 v2 = new ResourcePoolConfig_V2("ResourcePoolConfig_V2");
    System.out.println(v2.toString());

    ResourcePoolConfig_V3 v3 = new ResourcePoolConfig_V3.Builder()
                                .setName("ResourcePoolConfig_V3")
                                .setMaxTotal(16)
                                .setMaxIdle(16)
                                .setMinIdle(15)
                                .builder();
    System.out.println(v3.toString());
  }

}
