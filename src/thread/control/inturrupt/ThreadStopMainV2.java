package thread.control.inturrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV2 {

  public static void main(String[] args) {
    MyTask task = new MyTask();
    Thread thread = new Thread(task, "work");
    thread.start();

    sleep(4000);
    log("작업 중단 지시 thread.interrupt()");

    thread.interrupt(); // 인터럽트 상태가 되고, 인터럽트 상태인 스레드를 호출하면 Exception 을 예외발생
    log("work 스레드 인터럽트 상태1=" + thread.isInterrupted());
  }

  static class MyTask implements Runnable {

    @Override
    public void run() {
      try {
        while (true) {
          log("작업 중");
          Thread.sleep(3000); // work 가 sleep 상태이기 때문에 즉각 종료되지 않는다.
        }
      } catch (InterruptedException e) {
        log("work 스레드 인터럽트 상태2=" + Thread.currentThread()
            .isInterrupted()); // 인터럽트가 걸리면 인터럽트 상태가 해제된다(true -> false)
        log("interrupt message=" + e.getMessage());
        log("state=" + Thread.currentThread().getState());
      }
      log("자원 정리");
      log("자원 끝");
    }
  }
}
