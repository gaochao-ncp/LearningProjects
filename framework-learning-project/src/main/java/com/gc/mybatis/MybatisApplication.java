package com.gc.mybatis;

import com.gc.mybatis.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gaochao
 * @create 2020-08-03 11:28
 */
public class MybatisApplication {

  public static void main(String[] args) throws IOException {

    try(InputStream is = Resources.getResourceAsStream("mybatis-config.xml")) {

       //加载 mybatis-config xml 配置文件 ，并创建 SqlSess onFactory 对象
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
      //创建 SqlSession 对象
      SqlSession sqlSession = sqlSessionFactory.openSession();

      Map<String,Object> param = new HashMap<>(1);
      param.put("id",2);

      User user = (User)sqlSession.selectOne("com.gc.mybatis.mapper.UserMapper.getUser", param);
      System.out.println("user.toString() = " + user.toString());

      sqlSession.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
