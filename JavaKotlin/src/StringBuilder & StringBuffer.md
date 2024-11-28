## StringBuilder & StringBuffer

**1. StringBuffer와 StringBuilder의 차이점은 무엇이고, 각각을 어떤 상황에서 사용하는 것이 적절한지 설명해주세요.**

    StringBuffer와 StringBuilder의 대표적인 차이점은 "동기화 여부"입니다.
    StringBuffer에서는 synchronized 키워드를 사용하기 때문에 여러 스레드가 동시에 접근하더라도 
    하나의 스레드만 자원을 얻기 때문에 thread-safe합니다. 하지만 StringBuilder는 동기화를 지원하지 않습니다.
    그래서 두 클래스 모두 문자열의 변경이 자주 발생하는 경우에 사용하되, StringBuffer는 멀티 스레드 환경에서 사용하는 것이 적절하고,
    StringBuilder는 단일 스레드 환경에서 사용하는 것이 적절합니다.

**2. StringBuffer와 StringBuilder에서의 동기화 작업이 문자열 조작에 어떤 영향을 미치는지 자세히 설명할 수 있나요? 또한, 단일 스레드 환경에서 StringBuffer를 사용하는 경우에는 어떤 문제가 발생할 수 있을까요?**

    StringBuffer는 내부적으로 모든 메서드가 syncrhonized 키워드로 보호되어 있기 때문에 한 스레드가 메서드를 실행하는 동안
    다른 스레드도 메서드를 실행하려 하면, 작업 중인 스레드가 작업을 끝낼 때까지 대기합니다.
    하지만 StringBuilder는 동기화를 지원하지 않기 때문에 멀티 스레드 환경에서 문자열을 조작한다면 기대와 다른 결과가 나타날 수 있습니다.
    StringBuffer를 단일 스레드 환경에서 사용한다면 동기화 작업으로 인한 성능 저하 현상이 발생할 수 있습니다.
    그래서 단일 스레드 환경에선 StringBuilder를 사용하는 것이 더 적절합니다.


<br>

### Java의 String

Java에서 String 변수에 값을 할당하는 방법은 두 가지임.

1. `리터럴 변수`를 대입하는 방법
2. `new 키워드`를 사용하는 방법

```java
String a = "abc";
String b = "abc";
String c = new String("abc");
String d = new String("abc");

// 1. a == b -> true
// 2. a == c -> false
// 3. c == d -> false
```
String을 `리터럴 값`으로 할당하는 경우엔 Heap 메모리 영역내의 특별한 공간인 `String constant pool`에 저장됨.

만약 String constant pool에 존재하는 리터럴 값을 사용하게 된다면,
새롭게 리터럴 값을 만드는 것이 아닌 현재 존재하는 값을 사용하게 됨. 
그래서 위 코드에서 `a == b`의 주소값 비교가 true가 되는 것임.

String을 `new 키워드`로 값을 할당하는 경우엔 일반적인 객체와 동일하게 `Heap 영역`에 동적으로 메모리 공간이 할당됨.

그래서 같은 문자열이더라도 new 키워드를 한 번 더 사용하게 되면,
같은 값이지만 다른 메모리 공간을 참조하게 됨.
그래서 위 코드의 `c == d`의 주소값 비교가 false가 되는 것임.

<br>

### StringBuilder vs StringBuffer

불변하는 String과 달리 `StringBuilder`와 `StringBuffer` 모두 크기가 유연하게 변하는 `가변성`을 가짐.

이유는 두 클래스 모두 `AbstractStringBuilder`라는 추상 클래스를 상속받아 구현되어 있기 때문임.
이 추상 클래스의 멤버 변수로는 다음 두 가지 변수가 존재함.

- value: 문자열의 값을 저장하는 byte 배열
- count: 현재 문자열 크기의 값을 가지는 int 변수

```java
public AbstractStringBuilder append(String str) {
    if (str == null) {
        return appendNull();
    }
    int len = str.length();
    ensureCapacityInternal(count + len);
    putStringAt(count, str);
    count += len;
    return this;
}
```
위와 같이 `append()`로 문자열을 추가하게 되면, 추가할 문자열의 길이만큼 현재 문자열을 저장하는 배열의 공간을 늘려주고,
늘려준 공간에 추가할 문자열을 넣어주는 방식으로 구현됨.

그래서 값이 변경되더라도 같은 주소공간을 참조하고, 값이 변경되는 가변성을 가지게 됨.

두 클래스의 기능은 동일하지만 `동기화 (Synchronization)`에서 차이점이 있음.

`StringBuilder`는 동기화를 지원하지 않지만, `StringBuffer`는 동기화를 지원하여 멀티 스레드 환경에서도 안전하게 동작함.

이유는 StringBuffer에서 `synchronized` 키워드를 사용하기 때문임.
Java에서 `synchronized` 키워드는 여러 개의 스레드가 한 개의 자원에 접근하려할 때, 
현재 데이터를 사용하는 스레드를 제외한 나머지 스레드들이 데이터에 접근할 수 없도록 막는 역할을 함.

<br>

### String, StringBuilder, StringBuffer의 사용처

- String: `변하지 않는 문자열`을 자주 사용할 경우
- StringBuilder: `단일 스레드 환경`과 문자열의 `추가/수정/삭제`가 빈번히 발생하는 경우
- StringBuffer: `멀티 스레드 환경`rhk 문자열의 `추가/수정/삭제`가 빈번히 발생하는 경우