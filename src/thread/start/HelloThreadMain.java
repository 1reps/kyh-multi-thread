package thread.start;

public class HelloThreadMain {

  public static void main(String[] args) {
    System.out.println(Thread.currentThread().getName() + ": main() start");

    HelloThread helloThread = new HelloThread();

    System.out.println(Thread.currentThread().getName() + ": start() 호출 전");

    // 'Thread-0' 이름을 부여하지 않을 시 자바가 임의의 이름을 부여한다. (ex. 'Thread-0', 'Thread-1', ...)
    // 'HelloThread' 의 'Thread-0' 스레드가 시작되면서 스레드가 run() 메서드를 호출한다.
    // 여기서의 핵심은 'main' 스레드가 'run()' 메서드를 실행하는게 아니라 'Thread-0' 스레드가 'run()' 메서드를 실행한다는 점
    // 'main' 스레드는 단지 'start()' 메서드를 통해 'Thread-0' 스레드에게 실행을 지시할 뿐.
    // 이제 'main' 스레드와 'Thread-0' 스레드는 동시에 실행된다.
    helloThread.start();

    System.out.println(Thread.currentThread().getName() + ": start() 호출 후");

    System.out.println(Thread.currentThread().getName() + ": main() end");
  }
}
