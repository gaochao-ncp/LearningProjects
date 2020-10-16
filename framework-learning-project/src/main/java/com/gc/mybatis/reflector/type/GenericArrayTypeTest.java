package com.gc.mybatis.reflector.type;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

/**
 * GenericArrayType 接口方法测试类
 * @author gaochao
 * @create 2020-08-14 16:08
 */
public class GenericArrayTypeTest<T> {

  // 属于 GenericArrayType
  private List<String>[] pTypeArray;
  // 属于 GenericArrayType
  private T[] vTypeArray;
  // 不属于 GenericArrayType
  private List<String> list;
  // 不属于 GenericArrayType
  private String[] strings;
  // 不属于 GenericArrayType
  private int[] ints;

  public void test(List<String>[] pTypeArray,T[] vTypeArray,List<String> list,String[] strings,int[] ints){
    this.pTypeArray=pTypeArray;
    this.vTypeArray=vTypeArray;
    this.list=list;
    this.strings=strings;
    this.ints=ints;
  }

  public static void main(String[] args) throws NoSuchMethodException {
    // 获取指定名字的方法
    Method method = GenericArrayTypeTest.class.getMethod("test", List[].class, Object[].class, List.class, String[].class, int[].class);
    Type[] genericParameterTypes = method.getGenericParameterTypes();
    for (Type type : genericParameterTypes){
      System.out.println("typeName: "+type.getTypeName()+"; instanceof GenericArrayType -->"+(type instanceof GenericArrayType));

    }

    //获取所有属性
    Field[] fields = GenericArrayTypeTest.class.getDeclaredFields();
    for (Field field:fields){
      System.out.println("FieldName: "+field.getName()+"; instanceof GenericArrayType -->"+(field.getGenericType() instanceof GenericArrayType));
    }
  }

}
