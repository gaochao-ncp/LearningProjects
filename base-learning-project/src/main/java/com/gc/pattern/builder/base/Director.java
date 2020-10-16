package com.gc.pattern.builder.base;

/**
 * 指挥者:负责对建造的零件进行组装
 *
 * @author: Administrator
 * @date: 2020-10-16 14:46
 * @version: 1.0
 */
public class Director {

  private IBuilder builder;

  public Director(IBuilder builder){
    this.builder=builder;
  }

  public Product getProduct(String a,String b,String c){
    Product product = new Product();
    product.setPart_a(builder.builderPartA(a));
    product.setPart_b(builder.builderPartB(b));
    product.setPart_c(builder.builderPartC(c));
    System.out.println(product.toString());
    return product;
  }

}
