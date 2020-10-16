package com.gc.xml.pfrom;

import lombok.Data;

/**
 * @author gaochao
 * @create 2020-09-28 16:33
 */
@Data
public class SysHead {
  private String serviceCode;
  private String consumerId;
  private String serviceScene;
  private String esbSeqNo;
  private String moduleId;
  private String programId;
  private String consumerSeqNo;
  private String orgSysId;
  private String consumerSvrId;
  private String wsId;
  private String tranDate;
  private String tranTimestamp;
  private String userLang;
  private String filePath;
  private String retStatus;
  private Ret ret;
}
