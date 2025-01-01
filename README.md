### 메모리 가시성
- volatile 또는 스레드 동기화 기법(synchronized, ReenkrantLock)을 사용하면 메모리 가시성 문제가 발생하지 않는다.

### 스레드 상태
- NEW, RUNNABLE, WAITING, TIMED_WAITNG, BLOCKED, TERMINATED

### 무한 대기, 공정성 이슈
- ReentrantLock

### 생산자 소비자 문제
- 생산자 중 한명이 락 획득 후 대기중에 놓기게되면?
  소비자는 대기중에 놓여있는 상태(wait(), notify())

### notify() 스레드 기아 문제
- 대기중인 queue에서 실행 순서를 얻지 못해서 계속적으로 대기중인 상태

### Lock Condition
- 생산자가 생산자를 꺠우고, 소비자가 소비자를 깨우는 비효율 문제를 어떻게 해결할 수 있을까?
- ReentrantLock(lock, unlock), Condition(await, signal)
- ReentrantLock에 획득한 락을 반납하고 condition에 보관, signal 획득한 락을 반납하고 빠져나온다.

### 대기 공간 분리(consumerCondition, producerCondition)
- 락은 하나지만 대기공간은 2개
- 생산자는 대기자에게 알리고, 대기자는 생산자에게 알림

### 프록시(Proxy)
- 클라이언트 -> 서버
- 클라이언트 -> 프록시(동기화) -> 서버
- 생성자에 원본 컬렉션을 주입시켜서 프록시 컬렉션에게 위임한다.
  위임 받은 참조 객체인 프록시 컬렉션이 락을 획득하고 반납하게 되며,
  실제 메서드는 원본 컬렉션의 메서드를 호출하게된다.
  결론적으로, 원본 컬렉션은 동기화가되지 않고 프록시 컬렉션만 동기화가되는 상태(*얕은 복사와 비슷한 개념)

### Executors 스레드 풀 전략
- newSingleThreadPool(): 단일 스레드 풀 전략
  o 간단한 테스트 용도로 주로 사용
- newFixedThreadPool(nThreads): 고정 스레드 풀 전략
  o 큐 사이즈에 제한이 없고 안정적인 방식
  x 서버의 자원은 여유가 있는데, 응답이 느릴 경우??
- newCachedThreadPool(): 캐시 스레드 풀 전략
  o 초과 스레드의 수는 제한이 없다.
  o 요청에 대한 대기가 없음
  o 기존 스레드를 사용하지 않고, 60초 생존 주기를 가진 초과 스레드만 사용
  x 점진적인 사용자 확대
  x 갑작스런 요청 증가 -> 시스템 다운(실무에선 모니터링)
  x 스레드는 메모리 사용량을 잡아먹음 -> 대량의 메모리 사용량이 늘어날 수 있음
- 세분화 전략
