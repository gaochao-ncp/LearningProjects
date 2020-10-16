package com.gc.sftp;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.ftp.Ftp;

/**
 * FTP连接测试
 * @author gaochao
 * @create 2020-09-09 11:25
 */
public class FtpTest {

  public static void main(String[] args) {
    Ftp ftp = new Ftp("11.8.126.49",21,"us030036","us030036");


    final String key = StrUtil.format("{}@{}:{}", "ROOT", "192.168.1.4", "661");
    System.out.println(key);
  }
}
