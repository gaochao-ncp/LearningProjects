package com.gc.pattern.template.jdbc;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * 操作数据库会员表
 * @author gaochao
 * @create 2020-09-27 17:05
 */
public class MemberMapper implements BaseMapper<Member> {

  @Override
  public Member mapToBean(ResultSet rs) throws Exception {
    //MemberMapper.handleRs(Member.class, rs)
    Member member = new Member();
    member.setId(rs.getInt("id"));
    member.setAge(rs.getInt("age"));
    member.setAddr(rs.getString("addr"));
    member.setName(rs.getString("name"));
    return member;
  }

  /**
   * 将结果集字段动态转换成实体类中的字段,没完善,不好用
   * @param cl
   * @param rs
   * @param <E>
   * @return
   * @throws Exception
   */
  private static <E> E handleRs(Class<E> cl,ResultSet rs) throws Exception {
    E e = null;
    if ( null != rs){
      e = cl.newInstance();
      //获取列集
      ResultSetMetaData metaData = rs.getMetaData();
      //获取列的数量
      int count = metaData.getColumnCount();
      for (int i = 0; i < count; i++) {
        String columnName = metaData.getColumnName(i + 1);
        Field field = cl.getDeclaredField(columnName);
        Method method = cl.getDeclaredMethod(columnName,field.getType());
        method.invoke(e,rs.getObject(columnName));
      }
    }
    return e;

  }
}
