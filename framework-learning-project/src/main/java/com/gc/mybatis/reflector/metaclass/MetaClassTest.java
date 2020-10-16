package com.gc.mybatis.reflector.metaclass;

import com.gc.mybatis.entity.User;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaClass;

/**
 * {@link MetaClass}
 * @author gaochao
 * @create 2020-09-15 22:31
 */
public class MetaClassTest {

  public static void main(String[] args) {

    MetaClass metaClass = MetaClass.forClass(User.class, new DefaultReflectorFactory());

    String name = metaClass.findProperty("tele.num");

  }
}
