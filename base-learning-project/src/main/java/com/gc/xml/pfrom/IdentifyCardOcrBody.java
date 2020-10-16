package com.gc.xml.pfrom;

import lombok.Data;

/**
 * @author gaochao
 * @create 2020-09-29 10:57
 */
@Data
public class IdentifyCardOcrBody {
  private String errorCode;
  private String msg;
  private String idNum;
  private String address;
  private String sex;
  private String birth;
  private String nation;
  private String name;
  private String expiryDate;
  private String issueAuthority;
  private String headImage;
}
