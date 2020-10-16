package com.gc.pattern.factory.abstrat;

/**
 * 抽象工厂:是spring中应用最广泛的接口
 * 易于拓展 但是不符合开闭原则,符合单一职责原则
 * 缺点是 每次新建产品族都要新增拓展,需要修改抽象工厂的接口,但是只要不频繁修改产品族,不符合开闭原则也是可以接受的.
 * @author gaochao
 * @create 2020-09-20 0:12
 */
public interface AbstractFactory {
  //将产品族的创建方法全部归在总工厂里面
  INote creatNote();

  ISource creatSource();

  IVideo creatVideo();

}
