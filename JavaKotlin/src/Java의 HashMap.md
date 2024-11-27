## Java의 HashMap

### 질문
**1. HashMap과 hashCode 메소드의 관련성에 대해 설명해주세요.**

    HashMap은 내부적으로 보조 해시 함수를 이용하고, 키의 hashCode가 여기에 사용됩니다.
    Java에서 HashMap은 Node의 배열로 데이터를 관리합니다.
    특정 데이터의 index를 빠르게 찾기 위해 보조 해시 함수가 사용되기 때문에 만약 객체의 동일성을 비교하는 equals() 메서드를 override 했다면, 
    hashCode() 메서드도 override가 필요합니다. HashMap과 같은 해시 자료구조에선 hashCode를 통해 객체의 index를 판단하기 때문입니다.

**2. HashMap의 데이터 조회 작업에서 해시 충돌이 발생하는 경우에 대비해서 Java에서는 어떻게 처리를 하나요? Java에서는 어떤 방식으로 해시 충돌을 해결하고 있나요?**

    HashMap에서는 Separate Chaining 방식을 사용해 해시 충돌을 해결합니다.
    보조 해시 함수로 찾은 index에 데이턱 이미 존재한다면, 해당 노드의 LinkedList 마지막에 데이터를 추가하고,
    조회 시에는 LinkedList를 순회하며 같은 Value가 있는지 확인합니다.
    Java에선 Open Addressing 대신 Separate Chaining 방식을 택했는데, 이유는 Open Addressing은 remove할 때, Dummy data를 남기므로 효율적이기 어렵고, 
    해시 버킷을 채운 밀도가 높아질수록 worst case가 빈번하게 발생할 수 있기 때문입니다.

**3. Java에서 Separate Chaining을 구현할 때 Dummy 데이터를 어떻게 처리하고 있나요? Separate Chaining을 사용하면서 고려해야 할 성능 상의 이슈가 있을까요?**

    Separate Chaining에서는 데이터가 삭제될 때, Dummy data 대신 해당 LinkedList의 맨 끝에 데이터를 추가합니다.
    Separate Chaining을 사용할 때 Worst Case는 모든 데이터가 한 LinkedList에 연결될 수 있다는 점입니다. 
    그래서 고른 분포를 가진 보조 해시 함수를 고려해야 합니다.
    Java에서는 Separate Chaining의 성능을 더 끌어올리기 위해 특정 index에 해시 충돌이 일정 수 이상 발생한다면,
    LinkedList를 Tree로 변형하여 조회 성능을 향상시켰습니다.

<br>

### HashMap과 HashTable
`HashMap`과 `HashTable` 모두 Java Collections Framework에 속한 API임.

Key에 대한 해시 값을 사용하여 값을 저장하고 조회하며, 
키-값 쌍의 개수에 따라 동적으로 크기가 증가하는 associate array임.

두 자료구조 모두 `Map` 인터페이스를 구현하기 때문에 제공하는 기능은 같고,
조회/삽입/수정/삭제를 모두 `O(1)`에 수행함.

차이점은 `HashMap`은 `보조 해시 함수`를 사용하여 해시 충돌이 덜 발생하기 때문에
HashTable보다 성능상의 이점이 있고, 지속적으로 개선되고 있음.

반면에 `HashTable`은 `thread-safe`하여 멀티스레딩 환경에서 사용하기 적합함.

<br>

### HashMap의 보조 해시 함수

HashMap은 `보조 해시 함수`를 사용하여 키 값을 변형한 index 값의 분포를 균등하게 함.
즉, 이 과정으로 해시 충돌 가능성을 줄이는 것이며 성능을 개선할 수 있음.

```java
int index = key.hashCode() % M;
```
일반적으로 해시 함수를 이용하는 associative array에서는 키 값의 hashCode과 
table 크기 M의 나머지 연산을 통해 키에 해당하는 index를 구함.

```java
int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```
Java8부터는 상위 16비트 값을 XOR 연산하는 단순한 형태의 보조해시 함수를 사용함.

이유로는 Java8에서는 해시 충돌이 많아지면 LinkedList 대시 Tree를 사용해 성능 문제를 완화했고,
최근의 hashCode 함수는 균등 분포가 잘 되어 Java7까지 사용했던 복잡한 보조 해시 함수의 효과가 크지 않기 때문임.

Java에서 class의 `equals()`를 override 하면, `hashCode()`도 override 하라고 경고하는 이유가
바로 HashMap과 같은 Hash 자료구조의 보조 해시 함수에서 `hashCode()`를 통해 index를 구하기 때문에
개발자가 의도한 객체간 동일성을 Hash 자료구조에서도 유지하기 위해서임.

<br>

### HashMap의 해시 충돌 해결

보조 해시 함수로 index를 균등하게 분포시킨다고 해도 해시 충돌이 발생할 가능성이 존재함.

Java에서는 Open Addressing 대신 `Separate Chaining`을 사용해 해시 충돌을 해결함.

`Open Addressing`은 연속된 공간에 데이터를 저장하기 때문에 캐시 효율이 높지만,
table의 크기가 커질 수록 cache hit ratio가 낮아지기 때문에 이 장점은 사라짐.

또, Open Addressing은 데이터를 삭제할 때 Dummy 데이터를 남기기 때문에 삭제 효율이 떨어지는데,
HashMap에서는 `remove()` 메서드가 빈번히 호출될 수 있기 때문.

그리고 해시 버킷 밀도가 높아질수록 클러스터링이 발생하여 Worst Case 발생 빈도가 높아지기 때문에
`Separate Chaining` 방식이 사용됨.

<br>

### HashMap의 Node 클래스
```java
static class Node<K, V> implements Map.Entry<K, V> {
    final K key;
    V value;
    int hash;
    Node<K, V> next;

    Node(K key, V value, int hash, Node<K, V> next) {
        this.key = key;
        this.value = value;
        this.hash = hash;
        this.next = next;
    }
}

Node<K, V>[] table;
```
HashMap 내부적으로는 Key와 Value를 함께 저장하는 `Node<K, V> 배열`로 내부 테이블을 관리함.

Separate Chaining 방식으로 해시 충돌을 해결하기 때문에 해시 충돌 시, `next`를 통해 해당 LinkedList를 순회함.

Java7까지는 `Entry` 클래스를 사용하다가 Java8부터는 `Node` 클래스를 사용함.

내부 구현은 같지만 Java8부터는 Separate Chaining의 성능을 향상시키기 위해 
특정 Node에 해시 충돌이 6회 발생하면 LinkedList를 `Tree`로 변형시키는 `treeify`과정을 수행하기 때문임.

해당 `Node`는 `TreeNode`로 변형되고, 트리 탐색을 하기 때문에 LinkedList의 선형 탐색보다 성능이 향상됨.

참고로 사용되는 트리는 `Red-Black Tree`임.