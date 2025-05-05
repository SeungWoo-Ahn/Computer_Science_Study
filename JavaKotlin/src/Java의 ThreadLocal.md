## Java의 ThreadLocal

### 질문

**ThreadLocal은 무엇이며 왜 사용하나요?**

    ThreadLocal은 각 스레드마다 독립적인 값을 저장할 수 있는 클래스이고,
    공유자원 없이도 스레드 간 데이터 충돌 없이 데이터를 보관하고 사용할 수 있습니다.
    주로 사용자 인증 정보, DB 커넥션 등 스레드 종속적인 정보를 저장할 때 유용합니다.

<br>

**ThreadLocalMap 클래스가 어떻게 동작하며 왜 사용되는지 설명해보세요.**

    ThreadLocal은 ThreadLocalMap이라는 구조를 통해, 각 스레드 간 독립적인 맵에 데이터를 저장합니다.
    그래서 같은 ThreadLocal 인스턴스라도 스레드 간 데이터가 공유되지 않습니다.
    따라서 락 없이 데이터를 다룰 수 있는 장점이 있지만, 사용 후에는 remove를 호출해 메모리 누수를 방지해야 합니다.

<br>

### ThreadLocal

**사용 목적**

`ThreadLocal`은 각 스레드마다 독립적인 값을 저장할 수 있도록 하는 클래스임.

주 목적은 공유 자원 없이도 스레드 간 데이터 충돌 없이 데이터를 보관하고 사용하는 것임.

상황)
- 멀티 스레드 환경에서 스레드별 상태를 유지해야할 때
  - 사용자 인증 정보, DB 커넥션, 날짜 포맷터
- 공유 자원 없이 스레드별로 독립적인 값을 사용해야할 때

<br>

**작동 방식**

Thread 객체는 `threadLocals`라는 인스턴스 변수를 가지고 있는데,
ThreadLocal 내부의 `ThreadLocalMap`이라는 클래스를 이용해 key-value 데이터를 보관하고 있음.

ThreadLocal의 get, set 메서드들은 `currentThread()` 메서드를 통해 현재 수행중인 스레드를 통해 `ThreadLocalMap`을 찾아 반환함.

<br>

**주의 사항**

메모리 누수 위험이 존재함.

스레드가 오래 살아 있으면 ThreadLocal 값이 해제되지 않아 GC가 되지 않을 수 있어, 사용 후 remove() 호출을 권장함.