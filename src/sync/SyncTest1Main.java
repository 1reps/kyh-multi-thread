package sync;

import static util.MyLogger.log;

public class SyncTest1Main {

  public static void main(String[] args) throws InterruptedException {
    Counter counter = new Counter();

    MyTask task = new MyTask(counter);
    Thread t1 = new Thread(task, "task1"); // 10000번 호출
    Thread t2 = new Thread(task, "task2"); // 10000번 호출

    t1.start();
    t2.start();

    t1.join();
    t2.join();

    log("결과:" + counter.getCounter()); // 20000
  }

  static class MyTask implements Runnable {

    private final Counter counter;

    public MyTask(Counter counter) {
      this.counter = counter;
    }

    @Override
    public void run() {
      for (int i = 0; i < 10000; i++) {
        counter.increment();
      }
    }
  }

  static class Counter {

    private int counter;

    public synchronized void increment() {
      counter++;
    }

    public synchronized int getCounter() {
      return counter;
    }
  }
}
