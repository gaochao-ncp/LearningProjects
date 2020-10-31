package com.gc.spring.simple;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import com.gc.spring.simple.annotation.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: Administrator
 * @date: 2020-10-26 15:58
 * @version: 1.0
 */
@Slf4j
public class GcDispatcherServlet extends HttpServlet {

  private static final String FILE_FIX = ".class";

  /**
   * 配置文件
   */
  private Properties properties = new Properties();

  /**
   * 全类名
   */
  private List<String> classNames = new ArrayList<>();

  /**
   * ioc容器
   */
  private Map<String, Object> ioc = new ConcurrentHashMap<>();

  //spring中使用List保存url和method的映射关系,为什么不用map? Map因为key只能是url,而HandlerMapping本身就包括了url和method一一对应的关系,所以用List更符合单一职责原则和(迪米特原则)最少知道原则
  //private Map<String, Method> handlerMapping = new HashMap<>();
  private List<HandlerMapping> handlerMappings = new ArrayList<>();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      doDispatch(req, resp);
    } catch (Exception e) {
      e.printStackTrace();
      resp.getWriter().write("500 Exception: " + Arrays.toString(e.getStackTrace()));
    }
  }

  /**
   * 运行阶段
   * @param req
   * @param resp
   */
  private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    //客户端请求路径
    String requestURI = req.getRequestURI();
    String contextPath = req.getContextPath();
    HandlerMapping handlerMapping = getHandlerMapping(requestURI, contextPath);


    if (handlerMapping == null) {
      resp.getWriter().write("the url " + requestURI + " not found ! 404 !");
      return;
    }
    //根据路径找到对应的method并执行,并且传入正确的入参(这个是关键点)
    Method method = handlerMapping.getMethod();

    //从url中获取请求的实参列表
    Map<String, String[]> params = req.getParameterMap();

    //获取方法的形参列表
    Class<?>[] parameterTypes = handlerMapping.getParamTypes();

    //method方法的参数列表
    Object[] paramValues = new Object[parameterTypes.length];

    //获取对应的参数映射关系
    Map<String, Integer> paramIndexMapping = handlerMapping.getParamIndexMapping();

    //进行迭代给参数赋值
    for (Map.Entry<String, Integer> paramIndex : paramIndexMapping.entrySet()) {
      Integer index = paramIndex.getValue();
      String paramName = paramIndex.getKey();
      if (params.containsKey(paramName)){
        String value = Arrays.toString(params.get(paramName)).replaceAll("\\[|\\]", "").replaceAll("\\s", ",");
        paramValues[index] = convert(parameterTypes[index],value);
      }

    }

    if (paramIndexMapping.containsKey(HttpServletRequest.class.getName())){
      paramValues[paramIndexMapping.get(HttpServletRequest.class.getName())] = req;
    }

    if (paramIndexMapping.containsKey(HttpServletResponse.class.getName())){
      paramValues[paramIndexMapping.get(HttpServletResponse.class.getName())] = resp;
    }

    //先拿到方法所在的类,再通过类名拿到对应的实例
    String beanName = toLowerFirstCase(method.getDeclaringClass().getSimpleName());
    Object instance = ioc.get(beanName);
    if (instance == null) {
      resp.getWriter().write("The " + beanName + " instance is null!");
    } else {
      method.invoke(instance, paramValues);
    }

    //废弃的失败方法
    /*for (int i = 0; i < parameterTypes.length; i++) {
      Class<?> parameterType = parameterTypes[i];
      //因为是形参,所以不能用instance of
      if (parameterType == HttpServerResponse.class) {
        paramValues[i] = resp;
        continue;
      } else if (parameterType == HttpServerRequest.class) {
        paramValues[i] = req;
      } else {
        //对其他类型的参数进行处理
        //获得所有有注解的参数列表,为什么是个二维数组?因为一个参数可以有多个注解,方法又可以有多个参数;一个注解也可以加在多个字段上
        Annotation[][] pas = method.getParameterAnnotations();
        for (Annotation[] pa : pas) {

          for (Annotation annotation : pa) {
            //只解析GcRequestParam注解
            if (annotation instanceof GcRequestParam) {
              GcRequestParam gcRequestParam = (GcRequestParam) annotation;
              String paramName = gcRequestParam.value();
              if ("".equals(paramName)) {
                throw new Exception("GcRequestParam must set value!!!");
              }
              //从前台传来的参数列表中匹配对应名字的参数,进行遍历
              if (params.containsKey(paramName)) {
                //for (Map.Entry<String, String[]> stringEntry : params.entrySet()) {
                  //拿到key所对应的值,而拿到这个值,有一对多的关系
                  //一个key对应多个值,比如 http://ip:port/test/name=tom&name=nick&name=jack
                  System.out.println(Arrays.toString(params.get(paramName)));
                  //
                  String value = Arrays.toString(params.get(paramName)).replaceAll("\\[|\\]", "").replaceAll("\\s", ",");
                  paramValues[i] = convert(parameterType, value);
                //}
              }
            }
          }
        }
      }
    }*/

  }

  private HandlerMapping getHandlerMapping(String url,String contextPath){
    //对请求路径进行处理,将其转换为相对路径
    String requestURI = url.replaceAll(contextPath, "").replaceAll("/+", "/");
    for (HandlerMapping handlerMapping : this.handlerMappings) {
      if (StrUtil.isNotBlank(requestURI) && requestURI.equals(handlerMapping.getUrl())){
        return handlerMapping;
      }
    }
    return null;
  }

  /**
   * url传过来的参数都是String类型的，HTTP是基于字符串协议;只需要把String转换为任意类型就好
   *
   * @param type
   * @param value
   * @return
   */
  private Object convert(Class<?> type, String value) {
    //如果是int
    if (Integer.class == type) {
      return Integer.valueOf(value);
    }
    //如果还有double或者其他类型，继续加if
    //这时候，我们应该想到策略模式了
    //在这里暂时不实现，希望小伙伴自己来实现
    return value;
  }

  /**
   * 初始化阶段
   *
   * @throws ServletException
   */
  @Override
  public void init(ServletConfig config) {
    try {
      //1.加载配置文件
      doLoadConfig(config.getInitParameter("contextConfigLocation"));
      //2.扫描相关的类
      doScanner(properties.getProperty("scanPackage"));
      //3.初始化ioc容器
      doInstanceIoc();
      //4.依赖注入
      doAutowired();
      //5.初始化handlerMapping:url和method一一对应的关系
      initHandlerMapping();
      System.out.println("GC Spring framework is init.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 保存请求url和对应method的方法
   */
  private void initHandlerMapping() {
    if (ioc.isEmpty()) {
      return;
    }
    Set<Map.Entry<String, Object>> entries = ioc.entrySet();
    for (Map.Entry<String, Object> entry : entries) {
      //获取类上的 @GcRequestMapping 注解配置的url
      Class<?> cl = entry.getValue().getClass();
      String baseUrl = "";
      //判断类上是不是存在GcRequestMapping注解
      if (cl.isAnnotationPresent(GcRequestMapping.class)) {
        GcRequestMapping clUrl = cl.getAnnotation(GcRequestMapping.class);
        baseUrl = clUrl.value();
        //遍历类中方法上的url
        Method[] methods = cl.getMethods();
        for (Method method : methods) {
          if (method.isAnnotationPresent(GcRequestMapping.class)) {
            GcRequestMapping methodUrl = method.getAnnotation(GcRequestMapping.class);
            String url = ("/" + baseUrl + "/" + methodUrl.value()).replaceAll("/+", "/");
            handlerMappings.add(new HandlerMapping(url,method,entry.getValue()));
            System.out.println("mapping " + url + ":" + method);
          }
        }
      }
    }
  }

  private void doAutowired() {
    if (ioc.isEmpty()) {
      return;
    }
    Set<Map.Entry<String, Object>> entries = ioc.entrySet();
    entries.forEach((e) -> {
      //得到类声明的属性,包括private,public,default,protected的属性
      Field[] fields = e.getValue().getClass().getDeclaredFields();
      for (Field field : fields) {
        //只有存在 @GcAutowired 注解的属性才需要自动注入
        if (field.isAnnotationPresent(GcAutowired.class)) {
          //获得注解
          GcAutowired annotation = field.getAnnotation(GcAutowired.class);
          String key = toLowerFirstCase(annotation.value());
          if (StrUtil.isBlank(key)) {
            //没有指定beanName的话,默认使用全类名注入
            key = field.getType().getName();
          }

          //强吻:强制访问
          field.setAccessible(true);

          try {
            field.set(e.getValue(), ioc.get(key));
          } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
          }
        }
      }
    });

  }

  private void doInstanceIoc() {
    //通过反射创建实例,存放在Map中
    if (!classNames.isEmpty()) {
      //加了注解的类才需要初始化话
      classNames.stream().forEach((className) -> {
        try {
          Class<?> cl = Class.forName(className);
          if (cl.isAnnotationPresent(GcController.class)) {
            GcController annotation = cl.getAnnotation(GcController.class);
            putInstance(cl, annotation.value());
          } else if (cl.isAnnotationPresent(GcService.class)) {
            GcService annotation = cl.getAnnotation(GcService.class);
            putInstance(cl, annotation.value());
            //将接口全部扫描出来,将接口的全类名作为key
            for (Class<?> interfaces : cl.getInterfaces()) {
              // 不允许多个Service具有相同的名字
              if (ioc.containsKey(interfaces.getName())) {
                throw new Exception("The “" + interfaces.getName() + "” is exists!!");
              }
              ioc.put(interfaces.getName(), cl.newInstance());
            }
          } else {
            return;
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      });
    }
  }

  /**
   * 往ioc容器赋值
   *
   * @param cl
   * @param annotationValue
   */
  private void putInstance(Class<?> cl, String annotationValue) throws IllegalAccessException, InstantiationException {
    //如果没有给定bean指定名称的话,默认值取类名首字母小写
    String key = (StrUtil.isEmpty(annotationValue)) ? toLowerFirstCase(cl.getSimpleName()) : annotationValue;
    ioc.put(key, cl.newInstance());

  }

  private void doScanner(String scanPackage) {
    //scanPackage=com.gc.spring.simple,将 . 转换成 / 的路径,并且将类名存在一个List中
    //classPath 获取文件路径
    //这里的"/" 不能用File.separator替代,因为windows下面是 "\"
    URL resource = this.getClass().getClassLoader().getResource("/" +
            //在正则中 . 代表所有,要用转义符修复才行
            scanPackage.replaceAll("\\.", "/"));
    //获取类路径下的所有文件
    File classPathFile = new File(resource.getFile());
    for (File file : classPathFile.listFiles()) {
      if (file.isDirectory()) {
        //文件夹继续遍历
        doScanner(scanPackage + "." + file.getName());
      } else {
        //解析class文件,获取文件全类名;
        if (file.getName().endsWith(FILE_FIX)) {
          String classFullName = scanPackage + "." + file.getName().replaceAll(FILE_FIX, "");
          classNames.add(classFullName);
        }
      }
    }

  }

  private void doLoadConfig(String contextConfigLocation) throws IOException {
    //直接从类路径下找到主配置文件所在的路径
    InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
    //将其读取出来放到properties对象中
    properties.load(resourceAsStream);
  }

  /**
   * 将字符串的头字符转换为小写
   * 注意:请遵循驼峰命名,不然这个方法将报错
   *
   * @param str
   * @return
   */
  private String toLowerFirstCase(String str) {
    //这里需要做非空判断
    if (StrUtil.isBlank(str)) {
      return "";
    }

    char[] chars = str.trim().toCharArray();

    //判断头字母是不是大写,如果不是大写,不需要做转换
    if (chars[0] < 'A' || chars[0] > 'Z') {
      return String.valueOf(chars);
    }

    //在ASCII码中,大写字母和小写字母相差32位;并且大写字母的ASCII码比小写字母小
    //如果头字母是小写,那么将报错,所以要遵循规范
    chars[0] += 32;
    return String.valueOf(chars);
  }

  /**
   * 内部类:保存url和method的一一对应关系
   */
  @Getter
  private class HandlerMapping {
    //url
    private String url;
    //需要执行的method对象
    private Method method;
    //controller对象
    private Object controller;
    //形参列表:参数名字作为key,参数下标作为value
    private Map<String, Integer> paramIndexMapping = new HashMap<>();
    //形参列表
    private Class<?>[] paramTypes;

    public HandlerMapping(String url, Method method, Object controller) {
      this.url = url;
      this.method = method;
      this.controller = controller;
      this.paramTypes= method.getParameterTypes();
      putParamIndexMapping(method);
    }

    private void putParamIndexMapping(Method method) {
      //解析带有注解的对应参数的下标信息
      Annotation[][] pas = method.getParameterAnnotations();
      for (int i = 0; i < pas.length; i++) {
        Annotation[] pa = pas[i];
        for (Annotation annotation : pa) {
          if (annotation instanceof GcRequestParam){
            String value = ((GcRequestParam) annotation).value();
            if (!"".equals(value)){
              paramIndexMapping.put(value,i);
            }
          }
        }
      }

      //获取方法的形参的下标信息
      Class<?>[] parameterTypes = method.getParameterTypes();
      for (int i = 0; i < parameterTypes.length; i++) {
        Class<?> parameterType = parameterTypes[i];
        if ( parameterType == HttpServletRequest.class || parameterType == HttpServletResponse.class){
          paramIndexMapping.put(parameterType.getName(),i);
        }
      }
    }
  }
}
