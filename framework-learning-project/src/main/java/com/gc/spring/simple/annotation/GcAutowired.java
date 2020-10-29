package com.gc.spring.simple.annotation;

import java.lang.annotation.*;

/**
 * @author: Administrator
 * @date: 2020-10-26 16:48
 * @version: 1.0
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GcAutowired {
  String value() default "";
}
