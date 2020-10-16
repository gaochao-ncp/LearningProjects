package com.gc.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author gaochao
 * @create 2020-04-15 19:55
 */
@Configuration
@ComponentScan("com.gc.aop")
@EnableAspectJAutoProxy//开启aop注解
public class AopConfig {



}
