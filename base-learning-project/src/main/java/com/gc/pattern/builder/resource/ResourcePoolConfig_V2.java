package com.gc.pattern.builder.resource;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;

/**
 * 资源池配置类:目前已经解决了1版本会出现构造方法过程的问题了
 * 但是如果出现以下需求:
 * <p>
 *    1.如果必填的配置项有很多，把这些必填配置项都放到构造函数中设置，那构造函数就又会出现参数列表很长的问题。
 *    如果我们把必填项也通过set()方法设置，那校验这些必填项是否已经填写的逻辑就无处安放了。
 *    2.假设配置项之间有一定的依赖关系，当前设计模式下这些配置项之间的依赖关系或者约束条件的校验逻辑就无处安放了。
 *    3.我们希望ResourcePoolConfig对象是不可变对象,创建成功后无法改变属性:即不能暴露set()方法给用户
 * </p>
 * 我们利用建造者模式对代码进行升级: {@link ResourcePoolConfig_V3}
 * @author: Administrator
 * @date: 2020-10-22 14:40
 * @version: 1.0
 */
@Getter
public class ResourcePoolConfig_V2 {

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
  private int maxTotal = DEFAULT_MAX_TOTAL;
  /**
   * 最大空闲资源数量
   */
  private int maxIdle = DEFAULT_MAX_IDLE;
  /**
   * 最小空闲资源数量
   */
  private int minIdle = DEFAULT_MIN_IDLE;

  public ResourcePoolConfig_V2(String name) {
    if (StrUtil.isEmpty(name)){
      throw new IllegalArgumentException("name 不能为空");
    }
    this.name = name;
  }

  public void setMaxTotal(int maxTotal) {
    if (maxTotal <= 0){
      throw new IllegalArgumentException("maxTotal 必须大于0");
    }
    this.maxTotal = maxTotal;
  }

  public void setMaxIdle(int maxIdle) {
    if (minIdle < 0){
      throw new IllegalArgumentException("minIdle 必须为非负整数");
    }
    this.maxIdle = maxIdle;
  }

  public void setMinIdle(int minIdle) {
    if (maxIdle < 0){
      throw new IllegalArgumentException("maxIdle 必须为非负整数");
    }
    this.minIdle = minIdle;
  }

  @Override
  public String toString() {
    return "ResourcePoolConfig_V2{" +
            "name='" + name + '\'' +
            ", maxTotal=" + maxTotal +
            ", maxIdle=" + maxIdle +
            ", minIdle=" + minIdle +
            '}';
  }
}
