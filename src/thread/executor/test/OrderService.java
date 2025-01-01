package thread.executor.test;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class OrderService {

  // 주문 프로세스가 너무 오래걸림

  // 프로세스 1. 재고를 업데이트 해야한다. 약 1초
  // 프로세스 2. 배송 시스템에 알려야 한다. 약 1초
  // 프포세스 3. 회계 시스템에 내용을 업데이트 해야 한다. 약 1초 

  // 고객 입장에선 보통 3초의 시간을 대기해야 한다.
  // 호출 순서는 상관없지만 모두 성공해야 주문이 완료된다.
  // 기존 코드를 줄여보자

  public void order(String orderNo) throws ExecutionException, InterruptedException {

    ExecutorService executors = Executors.newFixedThreadPool(3);

    Future<Boolean> inventoryFuture = executors.submit(new InventoryWork(orderNo));
    Future<Boolean> shippingFuture = executors.submit(new ShippingWork(orderNo));
    Future<Boolean> accouningFuture = executors.submit(new AccountingWork(orderNo));

    // 결과 반환
    Boolean inventoryResult = inventoryFuture.get();
    Boolean shippingResult = shippingFuture.get();
    Boolean accountingResult = accouningFuture.get();

    if (inventoryResult && shippingResult && accountingResult) {
      log("모든 주문 처리가 성공적으로 완료되었습니다.");
    } else {
      log("일부 작업을 실패하였습니다.");
    }

    executors.close();
  }

  static class InventoryWork implements Callable<Boolean> {

    private final String orderNo;

    public InventoryWork(String orderNo) {
      this.orderNo = orderNo;
    }

    @Override
    public Boolean call() {
      log("재고 업데이트: " + orderNo);
      sleep(1000);
      return true;
    }
  }

  static class ShippingWork implements Callable<Boolean> {

    private final String orderNo;

    public ShippingWork(String orderNo) {
      this.orderNo = orderNo;
    }

    @Override
    public Boolean call() {
      log("배송 시스템 알림: " + orderNo);
      sleep(1000);
      return true;
    }
  }

  static class AccountingWork implements Callable<Boolean> {

    private final String orderNo;

    public AccountingWork(String orderNo) {
      this.orderNo = orderNo;
    }

    @Override
    public Boolean call() {
      log("회계 시스템 업데이트: " + orderNo);
      sleep(1000);
      return true;
    }
  }
}

