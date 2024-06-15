## Primitive 타입과 Reference 타입

### 질문

**1. Primitive 타입과 Reference 타입의 차이점은 무엇이며, 각각의 예시를 들어 설명해주세요.**

    Primitive 타입은 정수, 문자 등의 실제 데이터 값을 저장하는 타입입니다.
    예시로는 boolean, char, byte, short, int, long, float, double이 있습니다.
    Reference 타입은 메모리 번지 값을 통해 객체를 참조하는 타입입니다.
    예시로는 Boolean, Character, Byte, Short, Integer, Long, Float, Double이 있습니다.
    제너릭 타입에선 Primitive 타입은 들어올 수 없고 대신 Wrapper 클래스인 Reference 타입으로 사용할 수 있습니다.
    또 Primitive 타입에선 null값을 담을 수 없지만 Reference 타입에선 가능합니다.

### Primitive 타입

| 데이터형    | 크기(byte) | 표현 범위                                                   |
|---------|----------|---------------------------------------------------------|
| boolean | 1byte    | true or false                                           |
| char    | 2byte    | '\u0000' (0) ~ 'uFFFF' (65,535) <br/>(16비트 유니코드 문자 데이터) |
| byte    | 1byte    | -2^7 ~ 2^7 - 1                                          |
| short   | 2byte    | -2^15 ~ 2^15 -1                                         |
| int     | 4byte    | -2^31 ~ 2^31 - 1 (약 21억)                                |
| long    | 8byte    | -2^63 ~ 2^63 - 1                                        |
| float   | 4byte    | 1.4E-45 ~ 3.4028235E38                                  |
| double  | 8byte    | 4.9E-324 ~ 1.7976931348623157E308                       |

    실제 데이터 값을 저장하는 타입

    Primitive 타입은 반드시 사용하기 전에 선언되야함

    자료형의 길이는 운영체제에 독립적임

    '스택 메모리'에 저장됨

<br>

### Reference 타입

    메모리 번지 값을 통해 객체를 참조하는 타입

    Primitive 타입을 제외한 타입들

    Java의 최상위 클래스인 Object 클래스를 상속하는 모든 클래스

    실제 객체는 '힙 메모리'에 저장되며 참조 타입 변수는 '스택 메모리'에 객체 주소 저장
    객체를 사용할 때마다 참조 변수에 저장된 객체의 주소를 불러와 사용

    Garbage Collector가 돌면서 메모리를 해제함

<br>

### Primitive 타입 vs Reference 타입

|                | Primitive 타입 | Reference 타입 |
|----------------|--------------|--------------|
| null 포함 가능     | X            | O            |
| 제너릭 타입에서 사용 가능 | X            | O            |
| 저장 위치          | 스택 메모리       | 힙 메모리        |

<br>

### String Class

    String 클래스는 Reference 타입에 속함
    하지만 기본적인 사용은 Primitive 타입처럼 사용함
    
    불변하는 객체임, 왜?
    1) 메모리 관리
    String은 Heap 메모리의 String만을 위한 전용 메모리 공간인 'String Constant Pool'에 저장됨
    그래서 리터럴로 선언하면 JVM은 String constant pool에서 같은 값이 있는지 찾아봄
    따라서 Java에선 String 간 비교는 '==' 대신 'equals()'를 사용해야 함
    String은 자주 사용되는데 생성할 때마다 다른 메모리 영역을 가진다면 메모리의 낭비이기 때문임

    2) 보안
    String으로 사용자 ID, 비밀번호 등 민감한 정보를 다룸
    만약 String이 가변이라면 데이터의 무결성을 보장할 수 없기 때문

    3) 동기화
    다중의 스레드가 접근할 때에도 불변하기 때문에 String을 스레드 세이프하게 만들어줌

    4) 해시코드 캐싱을 통한 성능향상
    Hash 구현체(HashMap, HashSet 등)에서 bucket을 채우면서 hashCode() 메서드를 호출함
    String 객체는 불변성을 보장하기 때문에 String 객체를 사용하는 Hash 구현체의 성능을 향상시킴

    