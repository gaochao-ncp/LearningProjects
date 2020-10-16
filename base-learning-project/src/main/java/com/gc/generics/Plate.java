package com.gc.generics;

import lombok.Data;

/**
 * @author gaochao
 * @create 2020-07-30 20:26
 */
@Data
public class Plate<T> {

  private T t;

  public static void main(String[] args) {

    Plate<Banana> b = new Plate<>();
    b.setT(new Banana());

    //编译直接不通过,因为类型不同
    //1.苹果 IS A 水果
    //2.装苹果的盘子 IS NOT A 装水果的盘子
    //p = new Plate<Fruit>();
    //使用上界通配符来解决上述问题
    Plate<? extends Fruit> fruit = b;
    System.out.println(fruit.getT().toString());
    //fruit.setT(new Fruit());


    Apple apple = new Apple();
    Plate<Apple> applePlate = new Plate<>();
    applePlate.setT(apple);
    sendPlate(applePlate);

    Banana banana = new Banana();
    Plate<Banana> bananaPlate = new Plate<>();
    bananaPlate.setT(banana);
    sendPlate(bananaPlate);

    //下界通配符
    Plate<Fruit> fruitPlate = new Plate<>();
    Plate<? super Apple> plate = new Plate<>();

    //只能插入本身类和本身类的子类
    //plate.setT(new Apple());

    //插入父类失败
    //plate.setT(new Fruit());
    //plate.setT(new Object());

    //无法进行get
    //Apple apple1 = plate.getT();




  }


  public static void sendPlate(Plate<?> plate){
    System.out.println("盘子里装的是 ---->"+plate.getT().toString());
  }

}
