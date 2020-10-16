package com.gc.pattern.template.jdbc;

import java.sql.ResultSet;

/**
 * @author gaochao
 * @create 2020-09-27 16:25
 */
public interface BaseMapper<T> {

  /**
   * 解析rs,自行实现接口
   * @param rs
   * @return
   * @throws Exception
   */
  T mapToBean(ResultSet rs) throws Exception;

}
