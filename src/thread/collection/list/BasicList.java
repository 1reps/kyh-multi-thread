package thread.collection.list;

import static util.ThreadUtils.sleep;

import java.util.Arrays;

public class BasicList implements SimpleList {

  private static final int DEFAULT_CAPACITY = 5;

  private Object[] elementDate;
  private int size = 0;

  public BasicList() {
    elementDate = new Object[DEFAULT_CAPACITY];
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void add(Object o) {
    elementDate[size] = o;
    sleep(100); // 멀티스레드 문제를 쉽게 확인하는 코드
    size++;
  }

  @Override
  public Object get(int index) {
    return elementDate[index];
  }

  @Override
  public String toString() {
    return Arrays.toString(Arrays.copyOf(elementDate, size)) + " size=" + size + ", capacity="
        + elementDate.length;
  }
}
