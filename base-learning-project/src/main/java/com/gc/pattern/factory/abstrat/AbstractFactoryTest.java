package com.gc.pattern.factory.abstrat;

/**
 * @author gaochao
 * @create 2020-10-09 23:42
 */
public class AbstractFactoryTest {

  public static void main(String[] args) {

      AbstractFactory abstractFactory;

      INote iNote ;
      ISource iSource ;
      IVideo iVideo ;

      //java抽象工厂
      abstractFactory = new AbstractJavaFactory();
      iNote = abstractFactory.creatNote();
      iNote.creatNote();
      iSource = abstractFactory.creatSource();
      iSource.creatSource();
      iVideo = abstractFactory.creatVideo();
      iVideo.creatVideo();

      //php抽象工厂
      abstractFactory = new AbstractPhpFactory();
      iNote = abstractFactory.creatNote();
      iNote.creatNote();
      iSource = abstractFactory.creatSource();
      iSource.creatSource();
      iVideo = abstractFactory.creatVideo();
      iVideo.creatVideo();

  }
}
