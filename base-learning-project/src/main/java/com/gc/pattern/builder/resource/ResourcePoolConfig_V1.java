package com.gc.pattern.builder.resource;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;

/**
 * 资源池配置类
 * 思考:
 * <p>
 *   1.参数过多,导致构造函数传参过多,且只有name属性是必传的,所以可以通过暴露出其他属性的set方法来让用户一个个赋值,让用户自主选择.
 *   2.构造方法参数过多,到后期容易造成入参错误,形成一个非常隐形的bug
 * </p>
 * 升级后的代码: {@link ResourcePoolConfig_V2}
 * @author: Administrator
 * @date: 2020-10-22 14:40
 * @version: 1.0
 */
@Getter
public class ResourcePoolConfig_V1 {

  private static final int DEFAULT_MAX_TOTAL = 8;
  private static final int DEFAULT_MAX_IDLE = 8;
  private static final int DEFAULT_MIN_IDLE = 0;

  /**
   * 资源名称
   */
  private String name;
  /**
   * 最大资源总数
   */
  private int maxTotal ;
  /**
   * 最大空闲资源数量
   */
  private int maxIdle ;
  /**
   * 最小空闲资源数量
   */
  private int minIdle ;

  public ResourcePoolConfig_V1(String name, int maxTotal, int maxIdle, int minIdle) {
    if (StrUtil.isEmpty(name)){
      throw new IllegalArgumentException("name 不能为空");
    }
    this.name = name;
    if (maxTotal <= 0){
      this.maxTotal = DEFAULT_MAX_TOTAL;
      //throw new IllegalArgumentException("maxTotal 必须大于0");
    }

    if (maxIdle < 0){
      this.maxIdle = DEFAULT_MAX_IDLE;
      //throw new IllegalArgumentException("maxIdle 必须为非负整数");
    }

    if (minIdle < 0){
      this.minIdle = DEFAULT_MIN_IDLE;
      //throw new IllegalArgumentException("minIdle 必须为非负整数");
    }

  }

  @Override
  public String toString() {
    return "ResourcePoolConfig_V1{" +
            "name='" + name + '\'' +
            ", maxTotal=" + maxTotal +
            ", maxIdle=" + maxIdle +
            ", minIdle=" + minIdle +
            '}';
  }
}
