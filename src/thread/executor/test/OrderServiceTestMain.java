package thread.executor.test;

import java.util.concurrent.ExecutionException;

public class OrderServiceTestMain {

  public static void main(String[] args) {
    String orderNo = "Order#1234";
    OrderService orderService = new OrderService();
    try {
      orderService.order(orderNo);
    } catch (ExecutionException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
