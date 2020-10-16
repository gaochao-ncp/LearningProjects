package com.gc.pattern.builder.base;

/**
 * 客户端测试类
 *
 * @author: Administrator
 * @date: 2020-10-16 14:49
 * @version: 1.0
 */
public class Client {

  public static void main(String[] args) {
    IBuilder builder ;
    Director director;

    //tcl产品
    builder = new BuilderTclImpl();
    director = new Director(builder);
    director.getProduct("电视","冰箱","空调");


    //华为产品
    builder = new BuilderHuaWeiImpl();
    director = new Director(builder);
    director.getProduct("手机","笔记本","台式电脑");

  }

}
