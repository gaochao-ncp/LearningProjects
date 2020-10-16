package com.gc.mybatis.reflector;

import lombok.Data;
import org.apache.ibatis.reflection.TypeParameterResolver;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author gaochao
 * @create 2020-08-27 10:27
 */
public class ResolveTypeTest {

  SubA<Long> sa = new SubA<>();

  public static void main(String[] args) throws Exception {

    Field f = A.class.getDeclaredField("map");
    System.out.println(f.getGenericType());
    System.out.println(f.getGenericType() instanceof ParameterizedType);

    //解析字段 sa
    //第一种方法:
    Type type = TypeParameterResolver.resolveFieldType(f,
            ParameterizedTypeImpl.make(SubA.class,new Type[]{Long.class},ResolveTypeTest.class));
    System.out.println(type.getClass());

    //第二种方法:
    Type type1 = TypeParameterResolver.resolveFieldType(f,
            ResolveTypeTest.class.getDeclaredField("sa").getGenericType());
    System.out.println(type1.getClass());

    //cls1.isAssignableFrom(cls2) 判断cls2是不是cls1的子类,是的话返回true
    System.out.println(A.class.isAssignableFrom(SubA.class));

  }


}

@Data
class A<K,V>{

  protected Map<K,V> map;

}

@Data
class SubA<T> extends A<T,T>{


}
