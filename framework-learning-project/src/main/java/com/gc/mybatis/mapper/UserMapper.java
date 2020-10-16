package com.gc.mybatis.mapper;

import com.gc.mybatis.entity.User;

/**
 * @author gaochao
 * @create 2020-08-05 11:56
 */
public interface UserMapper {

   /**
    * 通过id获取用户信息
    * @param id
    * @return
    */
   User getUser(int id);

}
