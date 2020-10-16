package com.gc.mybatis.jdbc;

import java.sql.*;

/**
 * 通过jdbc连接数据库
 * @author gaochao
 * @create 2020-09-15 0:45
 */
public class JdbcTest {

  public static void main(String[] args) throws ClassNotFoundException, SQLException {

    //1.导入jdbc驱动
    Class.forName("com.mysql.cj.jdbc.Driver");
    //2.通过DriverManager建立数据库连接
    Connection conn = DriverManager.getConnection("jdbc:mysql://123.56.106.192:3306/gc?useUnicode=true", "root", "123456");
    //3.创建statement
    Statement statement = conn.createStatement();
    //4.执行sql,获取结果集
    ResultSet rs = statement.executeQuery("SELECT * FROM gc_user");
    //5.操作结果集
    while (rs.next()){
      System.out.println(rs.getString("name")+"----"+rs.getInt("age"));
    }

    //6.关闭连接
    conn.close();

  }

}
