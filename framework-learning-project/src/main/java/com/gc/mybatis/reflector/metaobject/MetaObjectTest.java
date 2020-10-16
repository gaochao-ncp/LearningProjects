package com.gc.mybatis.reflector.metaobject;

import com.gc.mybatis.entity.User;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

/**
 * @author gaochao
 * @create 2020-09-03 10:17
 */
public class MetaObjectTest {

  public static void main(String[] args) {
    User U =new User();

    MetaObject metaObject = SystemMetaObject.forObject(U);

    metaObject.setValue("id","这是我设置的id");
    System.out.println(U.getId());

    User u1 = User.init(2);
    MetaObject m1 = SystemMetaObject.forObject(u1);
    MetaObject metaObject1 = m1.metaObjectForProperty("orders[0]");
    System.out.println("m1.getValue(\"orders[0].id\").toString() = " + m1.getValue("orders[0].id").toString());


  }
}
