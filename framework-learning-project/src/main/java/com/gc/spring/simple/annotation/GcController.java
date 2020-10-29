package com.gc.spring.simple.annotation;

import java.lang.annotation.*;

/**
 * @author: Administrator
 * @date: 2020-10-26 16:51
 * @version: 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GcController {
  String value() default "";
}
