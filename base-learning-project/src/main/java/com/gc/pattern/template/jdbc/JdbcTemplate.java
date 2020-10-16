package com.gc.pattern.template.jdbc;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * jdbc模板:封装所有的jdbc数据库操作.
 * jdbc查询数据库的处理流程是一样的
 * 只是每次的语句集和每次返回的对象是不一样的,需要子类根据需求配置,所以我们可以利用模板方法模式来设计业务场景
 * @author gaochao
 * @create 2020-09-27 15:21
 */
@Slf4j
public class JdbcTemplate {

  /**
   * 查询方法
   * @param sql
   * @param params
   * @param baseMapper
   * @return
   */
  public <E> List<E> executeQuery(String sql,Object[] params,BaseMapper<E> baseMapper){
    List list = new ArrayList();
    try {
      //1.创建数据源,获取数据库连接
      Connection conn = this.initConnection();
      //2.创建语句集
      PreparedStatement pstm = this.createPrepareStatement(conn,sql);
      //3.执行语句集
      ResultSet rs = this.doExecuteQuery(pstm, params);
      //4.解析结果集
      list = this.paresResultSet(rs, baseMapper);
      //5.关闭结果集
      closeResultSet(rs);
      //6.关闭语句集
      closeStatement(pstm);
      //7.关闭连接
      closeConnection(conn);
    } catch (Exception e) {
      log.error("执行数据库查询时出现错误:",e);
    }
    return (List<E>) list;
  }

  /**
   * 1.初始化连接
   * @return
   */
  protected Connection initConnection() throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection connection = DriverManager.getConnection("jdbc:mysql://123.56.106.192:3306/gc?useUnicode=true", "root", "123456");
    return connection;
  }

  /**
   * 2.创建语句集
   * @param conn
   * @param sql
   * @return
   */
  protected PreparedStatement createPrepareStatement(Connection conn ,String sql) throws SQLException {
    PreparedStatement preparedStatement = conn.prepareStatement(sql);
    return preparedStatement;
  }

  /**
   * 3.执行语句集
   * @param statement
   * @param params
   * @return
   */
  protected ResultSet doExecuteQuery(PreparedStatement statement,Object[] params) throws SQLException {
    if (ObjectUtil.isNotEmpty(params)){
      for (int i = 0; i < params.length; i++) {
        statement.setObject(i+1,params[i]);
      }
    }
    ResultSet resultSet = statement.executeQuery();
    return resultSet;
  }

  /**
   * 解析结果集
   * @param rs
   * @param baseMapper
   */
  protected <E> List<E> paresResultSet(ResultSet rs,BaseMapper<E> baseMapper) throws Exception {
    List<E> list = new ArrayList<E>();
    while (rs.next()){
      E e = baseMapper.mapToBean(rs);
      list.add(e);
    }
    return list;
  }

  protected void closeResultSet(ResultSet rs) throws SQLException {
    if (null != rs) rs.close();
  }

  protected void closeStatement(Statement sts) throws SQLException {
    if (null != sts) sts.close();
  }

  protected void closeConnection(Connection conn) throws SQLException {
    if (null != conn) conn.close();
  }




}
