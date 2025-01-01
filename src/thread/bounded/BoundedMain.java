package thread.bounded;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.ArrayList;
import java.util.List;

public class BoundedMain {

  public static void main(String[] args) {
    // 1. BoundedQueue 선택
    // BoundedQueueV1 queue = new BoundedQueueV1(2);
    // BoundedQueueV2 queue = new BoundedQueueV2(2);
    // BoundedQueueV3 queue = new BoundedQueueV3(2);
    // BoundedQueueV4 queue = new BoundedQueueV4(2);
    // BoundedQueueV5 queue = new BoundedQueueV5(2);
    // BoundedQueueV6_1 queue = new BoundedQueueV6_1(2);
    // BoundedQueueV6_2 queue = new BoundedQueueV6_2(2);
    // BoundedQueueV6_3 queue = new BoundedQueueV6_3(2);
    BoundedQueueV6_4 queue = new BoundedQueueV6_4(2);

    // 2. 생산자, 소비자 실행 순서 선택, 반드시 하나만 선택!
    producerFirst(queue); // 생산자 먼저 실행
    // consumerFirst(queue); // 소비자 먼저 실행
  }

  private static void producerFirst(BoundedQueue queue) {
    log("== [생산자 먼저 실행] 시작, " + queue.getClass().getSimpleName() + " ==");
    List<Thread> threads = new ArrayList<Thread>();
    startProducer(queue, threads);
    printAllState(queue, threads);
    startConsumer(queue, threads);
    printAllState(queue, threads);
    log("== [생산자 먼저 실행] 종료, " + queue.getClass().getSimpleName() + " ==");
  }

  private static void consumerFirst(BoundedQueue queue) {
    log("== [생산자 먼저 실행] 시작, " + queue.getClass().getSimpleName() + " ==");
    List<Thread> threads = new ArrayList<Thread>();
    startConsumer(queue, threads);
    printAllState(queue, threads);
    startProducer(queue, threads);
    printAllState(queue, threads);
    log("== [생산자 먼저 실행] 종료, " + queue.getClass().getSimpleName() + " ==");
  }

  private static void startProducer(BoundedQueue queue, List<Thread> threads) {
    System.out.println();
    log("생산자 시작");
    for (int i = 0; i < 3; i++) {
      Thread producer = new Thread(new ProduceTask(queue, "data" + i), "producer" + i);
      threads.add(producer);
      producer.start();
      sleep(100);
    }
  }

  private static void startConsumer(BoundedQueue queue, List<Thread> threads) {
    System.out.println();
    log("소비자 시작");
    for (int i = 0; i < 3; i++) {
      Thread producer = new Thread(new ConsumerTask(queue), "consumer" + i);
      threads.add(producer);
      producer.start();
      sleep(100);
    }
  }

  private static void printAllState(BoundedQueue queue, List<Thread> threads) {
    System.out.println();
    log("현재 상태 출력, 큐 데이터: " + queue);
    for (Thread thread : threads) {
      log(thread.getName() + ": " + thread.getState());
    }
  }

}
