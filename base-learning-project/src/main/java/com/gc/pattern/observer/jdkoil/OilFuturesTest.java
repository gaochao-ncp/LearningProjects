package com.gc.pattern.observer.jdkoil;

/**
 * @author gaochao
 * @create 2020-10-09 10:52
 */
public class OilFuturesTest {

  public static void main(String[] args) {
    //观察者
    Bear bear = new Bear();
    Bull bull = new Bull();

    //被观察者
    OilFutures oilFutures1 = new OilFutures(3);
    OilFutures oilFutures2 = new OilFutures(-3);

    oilFutures1.addObserver(bear);
    oilFutures1.addObserver(bull);
    //进行广播
    oilFutures1.publish();

    oilFutures2.addObserver(bear);
    oilFutures2.addObserver(bull);
    //进行广播
    oilFutures2.publish();



  }
}
