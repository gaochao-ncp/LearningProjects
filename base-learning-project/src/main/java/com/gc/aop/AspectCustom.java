package com.gc.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 自定义切面类
 * @author gaochao
 * @create 2020-04-15 19:57
 */
@Aspect
@Component
@Slf4j
public class AspectCustom {

  /**
   * 切入点
   * 抽取出来的公共切点
   * execution(public * *(..)) :拦截任意公共方法
   * 表达方式:
   *  1.在本类中的表达方式:方法名
   *  2.在其他类中的表达方式:全类名+方法名
   */
  @Pointcut(value = "execution(public * *(..))")
  public void pointCuts(){

  }

  /**
   * 前置通知
   */
  @Before("pointCuts()")
  public void beforeAdvice(JoinPoint jp){
    log.info("进入前置通知 方法名{"+jp.getSignature().getName()+"} 参数列表{"+ jp.getArgs() +"}");

  }

  /**
   * 后置通知
   */
  @After("com.gc.aop.AspectCustom.pointCuts()")
  public void afterAdvice(JoinPoint jp){
    log.info("进入后置通知 方法名{"+jp.getSignature().getName()+"} 参数列表"+ jp.getArgs());
  }

  /**
   * 正常返回通知
   */
  @AfterReturning(value = "pointCuts()",returning = "result")
  public void afterReturning(JoinPoint jp,Object result){
    log.info("进入正常返回通知 方法名{"+jp.getSignature().getName()+"} 返回值{"+ result +"}");
  }

  /**
   * 异常通知
   */
  @AfterThrowing(value = "pointCuts()",throwing ="e")
  public void afterThrowing(JoinPoint jp,Exception e){
    log.info("进入异常通知 方法名{"+jp.getSignature().getName()+"} 异常{"+ e.getMessage() +"}");
  }

//  /**
//   * 环绕通知
//   */
//  @Around("pointCuts()")
//  public void aroundAdvice(){
//  }

}
