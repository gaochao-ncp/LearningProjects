package com.gc.xml.pfrom;

import lombok.Data;

import java.util.List;

/**
 * @author gaochao
 * @create 2020-09-28 17:28
 */
@Data
public class IdentifySingleCheckBody {

  private String ver;
  private String msgFlow;
  private String msgType;
  private String reserve;
  private String entrustDate;
  private String sts;
  private String rjctInf;
  private String resultFlow;
  private String result;
  private String msg;
  private List<TranListArray> tranListArray;
}
