package com.gc.mybatis.reflector.property;

import com.gc.mybatis.reflector.Person;
import org.apache.ibatis.reflection.property.PropertyCopier;

/**
 * @author gaochao
 * @create 2020-08-31 10:26
 */
public class PropertyTest {

  public static void main(String[] args) {
    Person p = new Person("CN",18);

    Person p1 = new Person();

    PropertyCopier.copyBeanProperties(Person.class,p,p1);


  }

}
