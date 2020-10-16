package com.gc.pattern.proxy.dynamicproxy.cglibproxy;

import com.gc.pattern.proxy.Woman;
import org.springframework.cglib.core.DebuggingClassWriter;

/**
 * {@link CglibMeipo} 测试demo
 * @author gaochao
 * @create 2020-09-25 11:50
 */
public class CglibMeipoTest {

  public static void main(String[] args) {

    //利用cglib的代理类,可以将内存中的class文件写入磁盘,然后通过jad反编译
    System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"E://cglib_proxy_class/");

    Woman woman = (Woman)new CglibMeipo().getInstance(Woman.class);
    //可以看到此时的Woman类并不是 com.gc.pattern.proxy.Woman 类了
    //而是cglib动态代理生成的 com.gc.pattern.proxy.Woman$$EnhancerByCGLIB$$37c31090 类,可以在我反编译的文件中找到
    System.out.println(woman.getClass());
    woman.findLove();
    //final方法无法代理(因为无法被重写),静态方法无法代理,final类更无法代理
    woman.eat();
    Woman.son();

  }

}
