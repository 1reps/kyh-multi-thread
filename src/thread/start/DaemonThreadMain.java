package thread.start;

public class DaemonThreadMain {

  public static void main(String[] args) {
    System.out.println(Thread.currentThread().getName() + ": main() start");
    DaemonThread daemonThread = new DaemonThread();
    daemonThread.setDaemon(false); // 데몬 스레드 여부
    daemonThread.start();
    System.out.println(Thread.currentThread().getName() + ": main() end");
  }

  static class DaemonThread extends Thread {

    @Override
    public void run() {
      try {
        System.out.println(Thread.currentThread().getName() + ": run()");
        Thread.sleep(10000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      System.out.println(Thread.currentThread().getName() + ": run() end");
    }
  }
}
