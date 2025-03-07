## Java의 synchronized

### 질문

**1. synchronized 키워드는 무엇이고 어떤 역할을 하는지 설명해주세요. 또한 synchronized 키워드를 이용하여 임계 영역을 지정할 때 주의할 점은 무엇인가요?**

    synchronized 키워드는 Java의 공유 자원에 대한 동기화를 수행합니다.
    스레드는 메서드 영역, 힙 영역 등을 공유하기 때문에 멀티스레드 환경에서 이 공유 자원에 대한 동기화가 필요하고,
    Java에선 synchronized 키워드로 동기화를 수행할 수 있습니다.
    임계 영역을 설정할 때는, synchronized 블록을 최소화하여 성능을 최적화하고,
    여러 개의 lock을 사용할 때는 데드락 방지를 위해 항상 같은 순서로 lock을 획득하도록 설정해야 합니다.

**2. synchronized 블록을 최소화하여 성능을 최적화하는 방법에 대해 어떤 전략이 있을까요? 또한, 데드락을 방지하기 위해 항상 같은 순서로 락을 획득하는 것이 왜 중요한지 설명해주세요.**

    synchronized 블록의 크기를 최소화할 수 있고, 상황에 맞는 동기화 전략을 선택할 수 있습니다.
    인스턴스 단위 동기화가 필요하다면 synchronized method를 사용할 수 있고,
    클래스 단위로 동기화가 필요하다면 static synchronized method를 사용할 수 있습니다.
    또 여러 개의 lock을 다른 순서로 사용한다면, 스레드가 서로의 자원을 얻지 못하는 교착 상태가 발생할 수 있기 때문에 
    같은 순서로 lock을 획득하는 것이 중요합니다.

<br>

### synchronized 키워드

스레드는 `스택 영역`만 각자 보유하고, 프로세스의 `메서드 영역`, `힙 영역`을 서로 공유함.

그래서 멀티스레드 환경에서 이 공유 자원에 대한 `동기화`를 처리해야 함.

Java에서는 `syncrhonized` 키워드를 이용해 동기화를 수행함.

<br>

### 4가지 synchronized 사용법

**1. synchronized method**

```java
private synchronized void sync() {}
```
메서드에 `synchronized`를 사용함.

해당 메서드를 보유한 클래스의 `인스턴스에 lock`을 거는 방법임.

주의할 것은 인스턴스 자체에 lock을 거는 것이 아니라,
synchronized 키워드가 붙은 메서드들에 대해서만 lock을 공유함.

<br>

**2. static synchronized method**

```java
private static synchronized void sync() {}
```

static 메서드에 `synchronized`를 사용함.

인스턴스 단위로 lock을 공유하는 것이 아니라, `클래스 단위로 lock`을 공유하는 방법임.

주의할 것은 인스턴스 단위로 거는 lock은 공유가 안되기 때문에,
혼용해서 사용한다면 동기화 이슈가 발생할 수 있음.

<br>

**3. synchronized block**

```java
private final Object o = new Object();

private void sync() {
    synchronized (this) {} // synchronized(this)
    synchronized (o) {} // synchronized(Object)
}
```

synchronized 인자로 `this`를 사용하면 모든 synchronized block에 lock이 걸림.

여러 스레드가 서로 다른 synchronized block을 호출해서 자기 자신에 lock을 걸었으므로 기다려야 함.

synchronized 인자로 `Object`를 사용하면 블록마다 다른 lock을 걸리게 만듦.

<br>

**4. static synchronized block**

static 메서드 안에 synchronized block을 지정함.

static의 특성상 `this` 키워드를 synchronized 인자로 사용할 수 없음.

lock 객체를 지정하고 block으로 범위를 한정지을 수 있음.

`클래스 단위로 lock`을 공유하는 것은 static synchronized method와 동일함.

<br>

### synchronized로 임계영역을 설정할 때 주의할 점

1. `synchronized` 블록을 최소화하여 성능을 최적화해야 함.
2. lock 객체를 신중하게 선택하고, 필요하면 별도의 객체를 lock으로 활용해야 함.
3. 여러 개의 lock을 사용할 때는 데드락 방지를 위해 항상 같은 순서로 획득해야 함.