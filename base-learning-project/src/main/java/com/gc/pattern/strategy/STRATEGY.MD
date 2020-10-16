策略模式定义:
> 是指将定义的一系列算法封装起来,提供给客户端转换调用,但是并不会影响最终结果.
> 属于行为型模式

在jdk中的应用场景
- Comparator接口
```java
public interface Comparator<T> { 
	int compare(T o1, T o2); 
	//... 
}
```

- Arrays类的parallelSort方法等
````java
public class Arrays {
  
public static <T> void parallelSort(T[] a, int fromIndex, int toIndex, Comparator<? super T> cmp) {
  

}

}
````

- TreeMap的构造方法
````java
public class TreeMap<K,V>
    extends AbstractMap<K,V>
    implements NavigableMap<K,V>, Cloneable, java.io.Serializable
{

    public TreeMap(Comparator<? super K> comparator) {
            this.comparator = comparator;
        }

}
````

在spring中的应用
- org.springframework.core.io.Resource类.虽然没有直接使用过Resource类,单使用过它的很多子类
```java
public interface Resource extends InputStreamSource {

}
```

- org.springframework.beans.factory.support.InstantiationStrategy接口:Spring初始化采用不同的策略

![InstantiationStrategy接口类图](./InstantiationStrategy.uml "InstantiationStrategy接口类图")

策略模式的优点:
- [X] 遵循开闭原则
- [X] 可以避免多重if...else...,switch判断语句
- [X] 可以提高算法的保护性和安全性

策略模式的缺点:
- [X] 委派者必须知道所有被委派者的功能(客户端必须知道所有的策略),然后根据对应的策略进行选择
- [X] 系统里面增加了很多策略类,影响代码的可读性和增加维护难度.
