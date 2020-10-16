package com.gc.pattern.template.jdbc;

import java.util.List;

/**
 * @author gaochao
 * @create 2020-09-27 15:18
 */
public class JdbcTemplateTest {
  static BaseMapper<Member> baseMapper = new MemberMapper();

  public static void main(String[] args) {

    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    String sql = "select * from member where name = ?";
    Object[] params = {"高潮"};
    System.out.println(params[0]);
    List<Member> list = jdbcTemplate.executeQuery(sql, params, baseMapper);
    System.out.println(list);

  }


}
