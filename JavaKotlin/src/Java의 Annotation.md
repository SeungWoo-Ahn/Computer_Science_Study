## Java의 Annotation

### 질문
**1. 어노테이션이란 무엇이며 자바에서 어떻게 활용되는지 설명해주세요.**

    Annotation은 Java 소스코드에 추가할 수 있는 일종의 '메타 데이터`입니다.
    Java에서 클래스, 인터페이스, 메서드 등에 붙여 단순 표식 혹은 값을 명시해 데이터를 전달하기 위해 사용합니다.
    Java에 내장된 Built-in Annotation 중 @Override는 오버라이딩하는 메서드 앞에 붙여 해당 메서드를 재정의했음을 컴파일러에게 명시하고,
    오탈자를 확인할 수 있습니다.

**2. 커스텀 Annotation을 만들 수 있는 방법에 대해 알고 있나요? 만약 알고 있다면, 어떻게 구현하는지 설명해주세요.**

    커스텀 Annotation을 만들기 위해선 '@interface` Annotation을 사용해야 합니다.
    커스텀 Annotation은 메타데이터 저장을 위한 Element를 가질 수 있고, 개수에 따라 종류가 나뉩니다.
    만약 메타데이터 없이 단순 표식을 위해서라면 Marker Annotation으로 사용할 수 있고,
    메타데이터가 필요하다면, 해당 메타데이터를 나타내는 타입과 이름을 메서드 형식으로 Annotaion 내에 기입할 수 있습니다.
    또 @Target으로 Annotation의 적용 범위를 설정하고, 
    @Retention을 Annotation 라이프 사이클을 지정하여 규칙을 정할 수 있습니다.

**3. 사용할 메타데이터 Element를 정의하고, 해당 Annotation을 특정 클래스나 메서드에 적용하는 방법에 대해 설명해주세요. 이 과정에서 어떠한 제약이 있을 수 있나요?**

    메타데이터 Element의 [타입] - [이름] 순으로 선언하고, 이름은 메서드 형식으로 나타내 Element를 정의합니다. 
    해당 Annotation을 적용하기 위해선 '@Annotation 이름'을 붙이고, 괄호 안에 Element 명과 input을 매개변수로 적용합니다.
    이 과정에서 @Retention을 RUNTIME으로 설정해야 Annotation 정보를 런타임에서 읽을 수 있다는 제약이 있습니다.

<br>

### Annotation이란?

Annotation은 Java 소스코드에 추가할 수 있는 일종의 `메타 데이터`임.

일반적으로 `클래스`, `인터페이스`, `메서드`, `변수`, `파라미터` 등에서 사용됨.

#### 장점
1. 빠른 속도

Annotation 프로세서는 실제로 javac의 일부이므로 모든 처리를 컴파일 타임에 수행함.

런타임이 아닌, 컴파일 타임에 작업을 수행해서 런타임 때 부담할 일을 줄여 더 빠른 속도의 처리가 가능함.

※ javac: jdk에 포함된 Java 컴파일러. 소스코드를 jvm에서 돌아가는 바이트코드로 변환

<br>

2. 리플렉션 없음

`리플렉션`은 구체적인 클래스 타입을 알지 못해도 그 내부에 있는 메서드, 타입, 변수를 접근하게끔 해주는 Java의 API임.

`javac`에 의해 바이트코드로 컴파일되어 Static 영역에 위치하는 클래스 정보를 읽어들여,
정확한 타입을 알지 못해도 리플렉션을 통해 그 클래스의 요소들을 사용할 수 있게 됨.

하지만, 요청이 발생할 때마다 클래스 정보를 읽어 처리하기 때문에 비용이 높은 작업임.

Annotation 프로세서는 이 리플렉션 없이 `Mirror API`를 사용해서 프로그램의 구조를 파악하기 때문에
보다 빠르고 효율적임.

※ Mirror API: 프로그램의 의미 구조를 가상머신 레벨이 아닌 언어 레벨에서 모델링 하기 위한 API.
클래스에 대한 런타임을 제공하지 않고 프로그램을 정적, 빌드시간 단위로 모델링함.

<br>

3. 보일러 플레이트 코드 제거

비슷한 형태로 반복되는 `보일러 플레이트 코드`를 자동을 생성할 수 있음.

이런 코드가 많아질수록 관리해야하는 포인트가 늘어나고, 이로 인한 버그 발생의 위험성이 높아짐.

Android 개발에서 `Room`, `Retrofit`, `Hilt` 등의 라이브러리들은 보일러 플레이트 코드를 자동으로 생성하고자 Annotation을 사용함.

<br>

### Annotation 처리 과정
Annotation은 `컴파일 타임`에 Annotation 프로세서에 의해 처리되고,
여러 라운드에 걸쳐 수행될 수 있음.

`라운드`는 개발자 혹은 라이브러리에서 정의된 Annotation의 `depth`에 따라 결정됨.

`첫 번째 프로세싱 라운드`가 시작되면, 실행되지 않은 Annotation 프로세서들이 소스코드에 있는 Annotation을 스캔하고,
처리하면서 보일러 플레이트 코드를 생성함.

만약 생성된 보일러 플레이트 코드에 또 다른 Annotation이 포함되어 있다면,
Annotation 프로세서는 다시 새로운 Annotation을 스캔하고, 작업을 수행하는 `두 번째 프로세싱 라운드`를 수행함.

이러한 반복은 Annotation 프로세서가 더 이상 처리할 Annotation이 없어질 때까지 반복함.

<br>

### Annotation의 종류
**1. Built-in Annotation**

Java에 내장되어 있고, 컴파일러를 위한 Annotation.

- @Override: 메서드 앞에 붙임으로써 현재 메서드가 super 클래스의 메서드를 재정의했음을 컴파일러에 명시하고, 재정의 시 오탈자를 막아줌.

- @Deprecated: 차후 버전에서 지원 중단 예정되기 때문에 더 이상 사용하지 말아야 할 메서드를 표시함.

- @SuppressWarnings: 컴파일러가 주는 경고 메세지를 개발자가 의도적으로 무시하고자 할 때 사용함.

- @NonNull: 매개변수로 Null을 넣지 못하게 경고하는 의미에서 사용함. 매개변수로 `null`을 넣으면 컴파일러가 경고를 표시함.

- @FunctionalInterface: 컴파일러에게 함수형 인터페이스(추상 메서드가 하나만 선언된 인터페이스)라는 것을 알려 입력 실수를 방지함.

**2. Meta Annotation**

Annotation을 위한 Annotation으로 해당 Annotation의 동작 대상, 스코프를 결정함.

- @Target: Annotation이 적용 가능한 대상을 지정하는데 사용함. 여러 대상을 지정해야 할 경우엔 `{}`로 묶어서 사용함.

- @Retention: Annotation의 `라이프 사이클`을 지정하기 위해 사용함.
  1. SOURCE: 소스파일에만 존재하는 Annotation, 컴파일 타임에 컴파일러에 의해 삭제됨.
  2. CLASS: 클래스 파일에만 존재하지만, 실질적으로 런타임까지 유지되지 않음.
  3. RUNTIME: 클래스 파일에 존재하며, 런타임 종료 시점까지 메모리가 유지됨.

- @Documented: Annotation에 대한 정보가 javadoc으로 작성한 문서에 포함되도록 하는 Annotation. `Build-in Annotation` 중 `@Override`와 `@SuppressWarnings`를 제외하고 모두 이게 붙어있음.

- @Inherited: Annotation을 자식 클래스에게도 붙이기 위해 사용하는 Annotation. super 클래스에 붙이면 자식 클래스에서도 이 Annotation이 붙은 것과 같음.

- @Native: `네이티브 메서드`에 의해 참조되는 상수 필드에 붙이는 Annotation.

※ 네이티브 메서드: JVM이 설치된 OS의 메서드. Java에서는 메서드의 선언부만 정의하고, 실질적인 구현은 C언어로 되어 있음.

**3. Custom Annotation**

개발자의 편의를 위해 정의하는 Annotation임.

특별한 종류의 인터페이스로, 일반 인터페이스와 구분하기 위해 `@`를 앞에 붙여 선언함.
```java
public @interface CustomAnnotation {}
```
암묵적으로 `java.lang.annotation.Annotation`을 확장하기 때문에 `extends`절을 갖지 못함.

메타데이터 저장을 위한 Element를 가질 수 있고, Element 개수에 따라 `Marker Annotation`, `Single-value Annotation`, `Full Annotatoin`으로 분류할 수 있음.

- Marker Annotation: Element가 없는 `단순 표식`으로 사용되는 Annotation, 컴파일러에게 의미를 전달하거나 주석 목적으로 사용함.

- Single-value Annotation: Element가 한 개인 Annotation. 값을 명시해 데이터를 전달하기 위해 사용.
```java
import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface POST {
    String value() default "";
}
```
- Full Annotation: Element가 둘 이상의 변수를 갖는 Annotation. 배열 안에 `key-value`형태로 전달하고, 허용되는 타입으로는 `기본형, String, enum, Annotation, Class`임.
```java
public @interface FullAnnotation {
    int count() default 1;
    String value();
}

// @FullAnnotation(count = 1, value = "input")과 같음.
@FullAnnotation(value = "input")
class TestClass {}
```
