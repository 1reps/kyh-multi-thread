package thread.collection.java;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetMain {

  public static void main(String[] args) {
    Set<Integer> copySet = new CopyOnWriteArraySet<>();
    copySet.add(1);
    copySet.add(2);
    copySet.add(3);
    System.out.println("copySet = " + copySet); // 정렬 순서 보장X

    Set<Object> skipSet = new ConcurrentSkipListSet<>(); // Comparator<? super E> comparator
    skipSet.add(1);
    skipSet.add(2);
    skipSet.add(3);
    System.out.println("skipSet = " + skipSet); // 정렬 순서를 보장O
  }
}
