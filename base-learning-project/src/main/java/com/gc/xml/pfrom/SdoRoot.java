package com.gc.xml.pfrom;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author gaochao
 * @create 2020-09-28 16:33
 */
@Data
@Accessors(chain = true)
public class SdoRoot<T> {

  private SysHead sysHead;
  private AppHead appHead;
  private LocalHead localHead;
  private T body;

}
