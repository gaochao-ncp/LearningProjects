package com.gc.ioc.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * （1）处理这2个注解的BeanPostProcessor不一样:
 *      CommonAnnotationBeanPostProcessor是处理@ReSource注解的;
 *      AutoWiredAnnotationBeanPostProcessor是处理@AutoWired注解的
 * （2）@Autowired只按照byType 注入；@Resource默认按byName自动注入，也提供按照byType 注入；
 * （3）属性：
 *      @Autowired按类型装配依赖对象，默认情况下它要求依赖对象必须存在，如果允许null值，可以设置它required属性为false。如果我们想使用按名称装配，可以结合@Qualifier注解一起使用。
 *      @Resource有两个中重要的属性：name和type。name属性指定byName，如果没有指定name属性，当注解标注在字段上，即默认取字段的名称作为bean名称寻找依赖对象，
 *      当注解标注在属性的setter方法上，即默认取属性名作为bean名称寻找依赖对象。
 *      需要注意的是，@Resource如果没有指定name属性，并且按照默认的名称仍然找不到依赖对象时，
 *      @Resource注解会回退到按类型装配。但一旦指定了name属性，就只能按名称装配了。
 *      @Resource装配顺序　　
 *        1. 如果同时指定了name和type，则从Spring上下文中找到唯一匹配的bean进行装配，找不到则抛出异常　　
 *        2. 如果指定了name，则从上下文中查找名称（id）匹配的bean进行装配，找不到则抛出异常　　
 *        3. 如果指定了type，则从上下文中找到类型匹配的唯一bean进行装配，找不到或者找到多个，都会抛出异常　　
 *        4. 如果既没有指定name，又没有指定type，则自动按照byName方式进行装配；如果没有匹配，则回退为一个原始类型进行匹配，如果匹配则自动装配；
 * @author gaochao
 * @create 2020-08-13 15:55
 */
@RestController
public class TestController {

  @Autowired
  @Qualifier("serviceImpl1")
  private Service service1;

  @Autowired
  @Qualifier("serviceImpl2")//当一个接口有多个类型的时候,需要使用该注解指定名字来进行注入
  private Service service2;

  @Resource(name = "serviceImpl1")
  private Service service3;

  @Resource(name = "serviceImpl2")
  private Service service4;

  @GetMapping("/getName")
  public List<String> getServiceName(){
    List<String> list = new ArrayList<>();
    list.add("service1 ==>"+service1.getName());
    list.add("service2 ==>"+service2.getName());
    list.add("service3 ==>"+service3.getName());
    list.add("service3 ==>"+service4.getName());
    return list;
  }
}
