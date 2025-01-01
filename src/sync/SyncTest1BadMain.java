package sync;

import static util.MyLogger.log;

public class SyncTest1BadMain {

  public static void main(String[] args) throws InterruptedException {
    Counter counter = new Counter();

    Runnable task = new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 10000; i++) {
          counter.increament();
        }
      }
    };

    Thread t1 = new Thread(task); // 10000번 호출
    Thread t2 = new Thread(task); // 10000번 호출

    t1.start();
    t2.start();

    log("t1 state=" + t1.getState());
    log("t1 state=" + t1.getState());

    t1.join();
    t2.join();

    log("결과:" + counter.getCounter()); // 20000
  }

  static class Counter {

    private int counter;

    public Counter() {
    }

    public void increament() {
      counter++;
    }

    public int getCounter() {
      return counter;
    }
  }
}
