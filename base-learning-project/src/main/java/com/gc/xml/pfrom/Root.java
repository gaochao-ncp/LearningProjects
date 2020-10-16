package com.gc.xml.pfrom;

import lombok.Data;

/**
 * 解析esb报文的根节点
 * @author gaochao
 * @create 2020-09-28 16:38
 */
@Data
public class Root {

  private SdoRoot sdoRoot;

  /**
   * 生成解析esb报文的节点
   * @param t
   * @param <T>
   * @return
   */
  public static <T> Root getRoot(T t){
    Root root = new Root();
    SdoRoot<T> sdoRoot = new SdoRoot<>();
    sdoRoot.setSysHead(new SysHead())
            .setAppHead(new AppHead())
            .setLocalHead(new LocalHead())
            .setBody(t);
    root.setSdoRoot(sdoRoot);
    return root;
  }

}
