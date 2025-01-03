package thread.bounded;

import static util.MyLogger.log;

public class ProduceTask implements Runnable {

  private BoundedQueue queue;
  private String request;

  public ProduceTask(BoundedQueue queue, String request) {
    this.queue = queue;
    this.request = request;
  }

  @Override
  public void run() {
    log("[생산 시도] " + request + " -> " + queue);
    queue.put(request);
    log("[생산 완료] " + request + " -> " + queue);
  }
}
