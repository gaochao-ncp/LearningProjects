package com.gc.mybatis.reflector.type;

import cn.hutool.core.util.ArrayUtil;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * TypeVariable类型方法的测试类
 * @author gaochao
 * @create 2020-08-14 11:56
 */
public class TypeVariableTest<Z extends Number & Serializable & Comparable> {

  private Z t;

  public static void main(String[] args) throws NoSuchFieldException {

    Field t = TypeVariableTest.class.getDeclaredField("t");
    TypeVariable type = (TypeVariable)t.getGenericType();
    Type[] bounds = type.getBounds();
    System.out.println("变量类型的上边界--->"+ArrayUtil.toString(bounds));

    GenericDeclaration genericDeclaration = type.getGenericDeclaration();
    System.out.println("声明该类型变量实体"+genericDeclaration);

    System.out.println("变量在源码中定义的名称"+type.getName());




  }

}
