package com.gc.xml;

import lombok.Data;

/**
 * 身份联网核查 接口返回的基础报文
 * @author gaochao
 * @create 2020-09-18 10:51
 */
@Data
public class IdentifyBase extends HeadRet {

  protected String ver;
  protected String msgFlow;
  protected String msgType;
  protected String reserve;
  protected String entrustDate;
  protected String sts;
  protected String rjctInf;
  protected String resultFlow;
  protected String result;
  protected String msg;

}
