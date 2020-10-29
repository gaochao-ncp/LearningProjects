package com.gc.pattern.builder.resource;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * 建造者模式实现
 * @author: Administrator
 * @date: 2020-10-22 15:13
 * @version: 1.0
 */
@Getter
public class ResourcePoolConfig_V3 {
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

  /** 私有构造方法,只能通过构建者模式创建对象 **/
  private ResourcePoolConfig_V3(){}

  public ResourcePoolConfig_V3(Builder builder){
    this.name= builder.name;
    this.maxTotal = builder.maxTotal;
    this.maxIdle = builder.maxIdle;
    this.minIdle = builder.minIdle;
  }

  @Override
  public String toString() {
    return "ResourcePoolConfig_V3{" +
            "name='" + name + '\'' +
            ", maxTotal=" + maxTotal +
            ", maxIdle=" + maxIdle +
            ", minIdle=" + minIdle +
            '}';
  }

  public static class Builder{
    private static final int DEFAULT_MAX_TOTAL = 8;
    private static final int DEFAULT_MAX_IDLE = 8;
    private static final int DEFAULT_MIN_IDLE = 0;
    private String name;
    private int maxTotal = DEFAULT_MAX_TOTAL;
    private int maxIdle = DEFAULT_MAX_IDLE;
    private int minIdle = DEFAULT_MIN_IDLE;

    public ResourcePoolConfig_V3 builder(){
      //校验逻辑放到这里来做，包括必填项校验、依赖关系校验、约束条件校验等
      if (StrUtil.isEmpty(this.name)){
        throw new IllegalArgumentException("name 不能为空");
      }
      if (this.maxTotal <= 0){
        throw new IllegalArgumentException("maxTotal 必须大于0");
      }
      if (this.minIdle < 0){
        throw new IllegalArgumentException("minIdle 必须为非负整数");
      }
      if (this.maxIdle < 0){
        throw new IllegalArgumentException("maxIdle 必须为非负整数");
      }
      if (maxIdle > maxTotal) {
        throw new IllegalArgumentException("maxIdle > maxTotal 非法 ");
      }
      if (minIdle > maxTotal || minIdle > maxIdle) {
        throw new IllegalArgumentException("minIdle 入参非法");
      }
      return new ResourcePoolConfig_V3(this);
    }

    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    public Builder setMaxTotal(int maxTotal) {
      this.maxTotal = maxTotal;
      return this;
    }

    public Builder setMaxIdle(int maxIdle) {
      this.maxIdle = maxIdle;
      return this;
    }

    public Builder setMinIdle(int minIdle) {
      this.minIdle = minIdle;
      return this;
    }
  }

}
