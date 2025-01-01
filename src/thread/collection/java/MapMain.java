package thread.collection.java;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class MapMain {

  public static void main(String[] args) {
    Map<Integer, String> map1 = new ConcurrentHashMap<>();
    map1.put(2, "data2");
    map1.put(3, "data3");
    map1.put(1, "data1");
    System.out.println("map1 = " + map1); // 순서보장X

    Map<Integer, String> map2 = new ConcurrentSkipListMap<>(); // Comparator<? super K> comparator
    map2.put(2, "data2");
    map2.put(3, "data3");
    map2.put(1, "data1");
    System.out.println("map2 = " + map2); // 순서보장O
  }
}
