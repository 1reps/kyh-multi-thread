package thread.start;

public class HelloRunnable extends Thread {

  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName() + ": HelloRunnable run()");
  }
}
