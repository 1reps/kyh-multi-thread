package thread.control.inturrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV1 {

  public static void main(String[] args) {
    MyTask task = new MyTask();

    new Thread(task, "work").start();

    sleep(4000);
    task.runFlag = false;
  }

  static class MyTask implements Runnable {

    volatile boolean runFlag = true;

    @Override
    public void run() {
      while (runFlag) {
        log("작업 중");
        sleep(3000); // work 가 sleep 상태이기 때문에 즉각 종료되지 않는다.
      }
      log("자원 정리");
      log("자원 끝");
    }
  }
}
