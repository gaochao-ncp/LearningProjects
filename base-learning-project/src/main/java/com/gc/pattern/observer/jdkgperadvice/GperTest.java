package com.gc.pattern.observer.jdkgperadvice;

/**
 * @author gaochao
 * @create 2020-10-09 11:09
 */
public class GperTest {

  public static void main(String[] args) {

    Gper gper = new Gper();

    Teacher teacher1 = new Teacher("Tom");
    Teacher teacher2 = new Teacher("Nick");

    Question question1 = new Question("小明","地球是什么形状的");
    Question question2 = new Question("小红","观察者模式的定义是什么");

    gper.addObserver(teacher1);
    gper.addObserver(teacher2);
    gper.publishQuestion(question1);
    //gper.publishQuestion(question2);



  }

}
