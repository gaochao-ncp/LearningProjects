package com.gc.pattern.observer.simple;

/**
 * @author gaochao
 * @create 2020-10-09 17:20
 */
public class Test {

  public static void main(String[] args) {
    ObserverListener listener = new ObserverListener();

    /*ConcreteObserver observer1 = new ConcreteObserver(new SimpleEvent("发布 EVENT_1"));
    ConcreteObserver observer2 = new ConcreteObserver(new SimpleEvent("发布 EVENT_2"));
    ConcreteObserver observer3 = new ConcreteObserver(new SimpleEvent("发布 EVENT_3"));
    listener.addObserver(SimpleEvent.EVENT_1,observer1);
    listener.addObserver(SimpleEvent.EVENT_2,observer2);
    listener.addObserver(SimpleEvent.EVENT_3,observer3);*/


    listener.addObserver(SimpleEvent.EVENT_1,"发布 EVENT_1");
    listener.addObserver(SimpleEvent.EVENT_2,"发布 EVENT_2");
    listener.addObserver(SimpleEvent.EVENT_3,"发布 EVENT_3");

    listener.publishObserver(SimpleEvent.EVENT_1);
    listener.publishObserver(SimpleEvent.EVENT_2);
    listener.publishObserver(SimpleEvent.EVENT_3);


  }

}
