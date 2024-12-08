## Java의 Thread

### 질문
**1. 자바의 스레드란 무엇이며, 스레드를 사용할 때의 장점과 단점은 무엇인가요?**

    Java에서 Thread는 JVM 프로세스 내에서 독립적으로 실행되는 작은 실행 단위를 의미합니다.
    Thread를 사용할 때의 장점은 처리량을 높힐 수 있습니다.
    프로세스 내에서 여러 스레드를 병렬적으로 실행할 수 있기 때문에 동시에 여러 작업을 처리해 처리량을 높아집니다.
    하지만 단점으로는 동기화 문제가 발생할 수 있습니다.
    Thread가 실행되는 순서는 예측할 수 없어 적절한 동기화 처리를 하지 않으면, Heap 영역과 같은 공유 자원에 쓰기를 수행할 때 원치 않는 결과를 얻을 수 있습니다.

**2. Java에서의 동기화 처리는 어떻게 이루어지며, Thread 간에 공유 자원을 안전하게 처리하기 위한 방법은 무엇이 있나요?**

    Java에서는 synchronized 키워드를 사용하여 동기화를 처리합니다.
    이를 통해 Thread가 공유 자원에 접근할 때, 한 번에 하나의 Thread만 자원에 접근할 수 있도록 보장하여 임계 영역을 설정하고 Thread-Saftey를 확보합니다.
    synchronized는 메서드 수준에서 사용하거나, 특정 객체를 기준으로 메서드 내에 synchronized 블록으로 사용해 동기화를 처리할 수 있습니다.

<br>

### Java에서 Thread

`스레드(Thread)`는 하나의 프로세스 안에서 독립적으로 실행되는 `작은 실행 단위`를 의미함.

Java에서는 `멀티 스레드`를 지원하여 하나의 프로세스 안에 한 개 이상의 스레드를 실행할 수 있음.
이를 통해 `비동기식 및 병렬 어플리케이션`을 개발할 수 있음.

<br>

### 프로세스 메모리 구조

| 구분       | 설명            | 공유 여부 | 동기화 필요여부 |
|----------|---------------|-------|----------|
| Code 영역  | 프로그램 코드       | 공유    | 불필요      |
| Data 영역  | 전역 변수 및 정적 변수 | 공유    | 불필요      |
| Heap 영역  | 동적 할당된 메모리    | 공유    | 필요       |
| Stack 영역 | 지역 변수 및 매개변수  | 비공유   | 불필요      |

**Code 영역**
- 프로그램의 `코드가 저장`됨.
- Thread 끼리 Code 영역을 공유하고, 읽기 전용이라 동기화는 필요 없음.

**Data 영역**
- `전역 변수와 정적 변수가 저장`되고, 해당 영역은 `프로그램이 실행되고 종료될 때까지 메모리에 존재`하는 영역임.
- Thread 끼리 Data 영역을 공유하고, 읽기 전용이라 동기화는 필요 없지만, 여러 Thread가 동시에 쓰는 경우에 동기화 문제가 발생할 수 있음.

※ 전역 변수: 클래스 내부 모든 메서드에서 사용 가능한 변수

※ 지역 변수: 메서드나 블럭 내에 선언된 변수로 블럭 안에서만 참조 가능함. 메서드가 호출될 때 생성되고, 종료될 때 소멸함.

**Heap 영역**
- `동적 할당된 메모리가 저장`되는 영역.
- Thread 끼리 Heap 영역을 공유하고, 여러 Thread가 동시에 쓰는 경우 동기화 문제가 발생할 수 있어 적절한 동기화 기법이 필요함.

**Stack 영역**
- 함수 호출 시 생성되는 `지역 변수와 매개변수가 저장`되고, 임시로 저장되었다가 `함수 호출이 끝나면 해당 영역의 메모리에서 해제`되는 영역.
- Thread 마다 독립적으로 존재하여 동기화는 필요 없음.

Thread가 실행되는 시간은 OS에 의해 관리되기 때문에 어떤 스레드가 먼저 실행될 지 예측할 수 없음.

따라서 여러 Thread가 동시에 하나의 자원에 접근하면, 결과를 예측할 수 없게 됨.

<br>

### Single Thread & Multi Thread
`Single Thread`는 하나의 프로세스 내에 하나의 Thread만 실행되기 때문에 공유 자원에 대한 동기화 문제가 없고,
실행 순서를 예측할 수 있음.

하지만 순차적으로 작업을 수행하기 때문에 처리량이 낮음.

`Mutli Thread`는 하나의 프로세스 내에 여러 개의 Thread가 실행되어 동시에 여러 작업을 처리하여 성능을 향상할 수 있음.

하지만 공유 자원에 대한 동기화 문제가 발생할 수 있고, 적절히 처리하지 않으면 원치 않는 결과를 얻을 수 있음.

<br>

### Java의 Thread 상태
1. `NEW`라는 상태로 Thread가 생성됨.
2. `RUNNABLE`이라는  실행이 가능한 상태가 됨.
3. Thread가 실행됨.
4. 실행 도중 각각의 상태가 될 수 있음.
   - `BLOCKED` : 일시적으로 중단됨.
   - `WAITING` : 다른 Thread의 작업이 완료될 때까지 기다림.
   - `TIMED_WAITING` : 일정 시간 동안 기다림.
   - `TEMINATED` : 실행이 완료됨.
5. 각각의 상태에서 복귀하면 다시 `RUNNABLE`로 대기 상태가 됨.

<br>

### Thread 클래스와 Runnable 인터페이스
**Thread 클래스**
```java
class MyThread extends Thread {
    public void run() { }
}

class Main {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();
    }
}
```
**Runnable 인터페이스**
```java
class MyRunnable implements Runnable {
    public void run() { }
}

class Main {
    public static void main(String[] args) {
        MyRunnable r = new MyRunnable();
        Thread t = new Thread(r);
        t.start();
    }
}
```
`Thread`와 `Runnable` 모두 `run()` 메서드를 오버라이딩하여 구현해 사용함.

`Runnable`은 상속한 클래스를 생성하여 `Thread(Runnable target)` 생성자로 Thread를 생성해 사용함.

`Runnable`을 사용하는 것이 `Thread`를 사용하는 것보다 선호되는데,
1. Java는 인터페이스를 제외하고 다중 상속을 지원하지 않음. 그래서 Thread 클래스를 사용하는 것은 다른 클래스를 상속할 수 없음.
2. Runnable 인터페이스는 Thread 뿐만 아니라 Executors 등에 의해 실행될 수 있음.
3. Runnable로 작업을 분리하면, 해당 작업을 재사용할 수 있음.

<br>

### Java에서의 동기화
동기화는 여러 Thread가 공유 자원에 접근할 때 발생할 수 있는 `데이터 불일치` 문제를 방지하는 메커니즘임.

동기화는 한 번에 하나의 Thread만 자원에 접근할 수 있도록 보장하여 `Thread-Saftey`를 확보함.

Java에서는 `synchronized` 키워드를 사용하여 동기화를 처리하고, 이를 통해 `임계 영역`을 설정할 수 있음.

**synchronized 키워드**

`synchronized`는 코드 블록 또는 메서드 수준에서 동기화를 적용할 수 있음.
```java
public class SharedResource {
    // 해당 한 번에 하나의 Thread만 실행할 수 있음
    public synchronized void increment() {
    }
    
    private final Object lock = new Object();
    private int counter = 0;
    
    // 특정 객체를 기준으로 동기화 범위를 지정함
    public void increment() {
        synchronized (lock) {
            counter++;
        }
    }
}
```

**ReentrantLock 사용**
- 락을 명시적으로 획득하고 해제할 수 있어 유연한 동기화 가능.
- 락 대기 시간 설정, 중단 가능 락 요청 등 고급 기능 지원.

```java
import java.util.concurrent.locks.ReentrantLock;

public class SharedResource {
    private final ReentrantLock lock = new ReentrantLock();
    private int counter = 0;

    public void increment() {
        lock.lock(); // 락 획득
        try {
            counter++;
        } finally {
            lock.unlock(); // 락 해제
        }
    }
}
```

**volatile 키워드 사용**

`volatile`은 변수가 여러 스레드에 의해 읽고 쓰일 때, 각 스레드가 변수 값을 CPU 캐시가 아닌 `메인 메모리`에서 직접 읽도록 보장함.
- 사용 상황: 간단한 읽기/쓰기 작업
- 주의: 복잡한 연산이나 `++`같은 작업은 데이터 불일치를 방지하지 못함.

```java
public class SharedResource {
    private volatile boolean flag = false;
    
    public void setTrue() {
        flag = true;
    }
    
    public boolean isTrue() {
        return flag;
    }
}
```