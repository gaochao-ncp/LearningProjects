package com.gc.easyexcel;

import java.util.List;

/**
 * @author: Administrator
 * @date: 2020-11-26 21:52
 * @version: 1.0
 */
public class DemoDAO {
  public void save(List<DemoData> list) {
    // 如果是mybatis,尽量别直接调用多次insert,自己写一个mapper里面新增一个方法batchInsert,所有数据一次性插入
    System.out.println("xxxx");
  }
}
