package com.gc.mybatis.reflector.type;

import cn.hutool.core.util.ArrayUtil;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Type的子类ParameterizedType练习
 * @author gaochao
 * @create 2020-08-09 23:02
 */
public class ParameterizedTypeTest {

    private HashMap<String,Object> map;
    private HashSet<String> set;
    private List<String> list;
    private Class<?> clz;

    private Integer i;
    private String str;

    private static void printParameterizedType(){
      Field[] fields = ParameterizedTypeTest.class.getDeclaredFields();
      for (Field field : fields) {
        // 打印是不是ParameterizedType类型
        System.out.println("Field name=>{"+field.getName()+"}  instance of ParameterizedType={" + (field.getGenericType() instanceof ParameterizedType)+"}");
      }
    }

    private static void getParameterizedTypeWithName(String name) throws NoSuchFieldException {

      Field f = ParameterizedTypeTest.class.getDeclaredField(name);

      ParameterizedType t = (ParameterizedType)f.getGenericType();

      System.out.println("变量{"+name+"}的所属类型为{"+t.getOwnerType()+"}");
      System.out.println("变量{"+name+"}的原始类型为{"+t.getRawType()+"}");
      System.out.println("变量{"+name+"}的参数类型为{"+ArrayUtil.toString(t.getActualTypeArguments())+"}");

    }


    public static void main(String[] args) throws NoSuchFieldException {

      printParameterizedType();;
      getParameterizedTypeWithName("map");
      getParameterizedTypeWithName("set");

    }
}
