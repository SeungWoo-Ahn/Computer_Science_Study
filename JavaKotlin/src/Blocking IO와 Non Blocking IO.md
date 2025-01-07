## Blocking I/O와 Non Blocking I/O

### 질문

**Blocking IO와 Non-Blocking IO의 차이점은 무엇이고, 각각의 장단점은 무엇인가요? 페이지 입출력에 대한 개념도 같이 설명해주세요.**

    Blocking IO와 Non-Blocking IO의 차이점은 제어권 여부에 있습니다.
    Blocking IO는 제어권이 없어 IO 작업이 완료될 때까지 호출 스레드가 대기 상태로 머물러 다른 작업을 할 수 없습니다. 
    많은 스레드를 사용하지 않아 리소스를 적게 사용하는 장점이 있지만, IO 작업 동안 대기하므로 CPU 리소스가 낭비되는 단점이 있습니다. 
    반면에 Non-Blocking IO는 제어권을 가져 IO 작업이 진행되는 동안 대기하지 않고 다른 작업을 수행할 수 있습니다. 
    대규모 병렬 처리가 가능한 장점이 있지만, 비동기 로직과 콜백 처리로 코드 복잡성이 증가한다는 단점이 있습니다. 
    Java에서 페이지 입출력은 IO 작업에서 데이터를 페이지 단위로 읽거나 써서 성능을 최적화하는 방법입니다.

<br>

### Block vs Non-Block
다른 주체가 작업할 때, 물리적으로 자신의 `제어권`이 있는지가 기준임.

**Blocking I/O**
- 자신의 작업을 진행하다가 다른 주체의 작업이 시작되면, 다른 작업이 끝날 때까지 `기다렸다가` 자신의 작업을 시작하는 것.
- `물리적`으로 I/O 작업이 끝날 때까지 다른 작업을 하지 않고 기다리는 것을 의미함.

**Non-Blocking I/O**
- 다른 주체의 작업에 `관련없이` 자신의 작업을 하는 것.
- I/O 작업이 진행되는 동안에도 `물리적`으로 `대기하지 않고`, 작업을 수행할 수 있음.

<br>

### Synchronous vs Asynchronous
`논리적`으로 I/O 작업의 `결과에 관심이 있는지 아닌지`가 기준임.

**Synchronous**
- `논리적`으로 I/O 작업으로부터 돌아올 결과를 기다리고 처리함.
- I/O 작업의 결과에 따라 처리해야 하므로, 순서는 한 I/O 작업 이후 하던 작업을 이어나감.

**Asynchronous**
- `논리적`으로 I/O 작업이 일어나는 동안 다른 작업을 수행할 수 있음.
- 돌아올 결과를 기다리지 않고 일을 하며, 돌아온 결과에 대해 처리를 할 수도 있고 `안할 수도 있음`.

<br>

### 조합
**Synchronous Blocking I/O**
- Blocking: 제어권이 없으므로, I/O 작업이 끝날 때까지 스레드에서 다른 작업을 하지 못함.
- Synchronous: 다른 작업으로부터 결과 반환 시 곧바로 처리함. 
- (예시) read/write

```java
import java.util.Scanner;

public static void main(String[] args) {
    final Scanner sc = new Scanner(System.in);
    final String input = sc.nextLine();
    System.out.println(input);
}
```

**Synchronous Non-Blocking I/O**
- Non-Blocking: 제어권을 잃지 않기 때문에 I/O 작업이 끝나기를 기다리지 않고, 스레드에서 다른 작업을 대기 없이 계속할 수 있음.
- Synchronous: 결과가 나왔는지 일정한 시간 간격으로 체크하고, 결과를 받으면 바로 업무를 처리함.
- (예시) polling, interrupt

`polling`: 주기적으로 특정 상태나 이벤트를 확인하는 방법.

`interrupt`: I/O가 완료되고 상태가 변경되었을 때, interrupt를 통해 알림을 받는 방법.

단점: 커널이 I/O 작업으로부터 response를 받는 시점과 애플리케이션이 data를 받는 시점 사이에 `time gap`이 지연을 유발함.

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public static void main(String[] args) {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    try {
        while (!br.ready()) {
            // 다른 작업 수행
            Thread.sleep(1_000);
        }
        String input = br.readLine();
        System.out.println(input);
    } catch (IOException | InterruptedException e) {
    }
}
```
**Asynchronous Blocking I/O**
- Blocking: 제어권이 없으므로, I/O가 끝나기만을 기다리면서 다른 작업을 할 수 없음.
- Asynchronous: 결과에 대한 처리를 할 수도 안할 수도 있음.

I/O 작업 결과를 바로 처리할 것도 아닌데 I/O 작업을 기다릴 이유가 없음.
그래서 해당 조합은 잘 사용되지 않음.

**Asynchronous Non-Blocking I/O**
- Non-Blocking: I/O 작업을 수행하는 동안 스레드는 기다리지 않고 다른 작업을 수행함.
- Asynchronous: 결과에 대한 처리를 할 수도 안할 수도 있음.

```java
import java.util.concurrent.CompletableFuture;

public static void main(String[] args) {
    CompletableFuture<String> futureResult = CompletableFuture.supplyAsync(() -> performAsyncTask());
    for (int i = 0; i < 10; i++) {
        // 비동기 작업을 대기하지 않고 다른 작업 수행
        Thread.sleep(500);
    }
    String result = futureResult.get();
    System.out.println(result);
}

private static String performAsyncTask() {
    try {
        Thread.sleep(3_000);
    } catch (InterruptedException e) {
    }
    return "비동기 작업 완료";
}
```