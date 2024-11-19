## Java의 ArrayList

### 질문
**1. ArrayList의 내부 구조에 대해 설명해주세요. 이 구조가 배열과 어떻게 다른지 알려주세요.**

    ArrayList는 내부적으로 Object 배열을 사용해서 자료를 저장합니다.
    일반 배열과 다른 점은 "동적으로 크기를 조절"한다는 점입니다.
    ArryaList의 add 메서드를 호출할 때, 내부 배열이 모두 차있는 것을 우선 확인하고, 만약 가득 찼다면
    기존 배열의 1.5~2배의 크기를 가지는 새로운 배열에 기존 배열의 각 요소를 복사하는 resize 과정이 수행됩니다.
    반대로 remove 메서드를 호출할 땐, size가 배열 크기의 절반 이하라면 내부 배열의 크기를 줄이는 resize 과정이 수행됩니다.

**2. ArrayList의 내부 배열이 꽉 찼을 때의 resize 과정에서 발생하는 시간 복잡도는 어떻게 되나요? 또한, ArrayList에서 요소를 제거할 때의 resize 과정에서 시간 복잡도가 어떻게 변화하나요?**

    resize 과정은 내부 배열의 각 요소를 resized된 배열에 복사하는 작업을 수행합니다.
    따라서 내부 배열이 꽉 찼거나 요소를 제거할 때, 배열의 크기인 size에 비례하는 O(size)의 시간복잡도를 갖습니다.

**3. 내부 배열의 크기 조절 과정에서 발생하는 메모리 할당과 복사 작업에서 어떤 최적화 기법들이 사용되는지 알고 있나요? 그리고 resize 과정에 대한 시간 복잡도 외에 다른 영향을 끼치는 요소가 있을까요?**

    먼저 ArrayList의 내부 배열 크기는 "기하급수적"으로 증가됩니다.
    기존 배열의 1.5~2배의 크기로 증가되기 때문에 한 번 크기가 증가하면, 이후 여러 번의 작업에서
    resize가 수행되지 않으므로 추가/삭제에 O(1)의 시간복잡도를 보장할 수 있습니다.
    또 "System.arraycopy()"를 이용합니다.
    Java의 배열은 메모리상 연속된 공간에 저장되는데, 메모리 블록 복사 방식을 사용해 연속적으로
    저장된 메모리 블록을 한 번에 복사하여 단순 반복문보다 빠르게 수행됩니다.
    resize 과정에서 시간 복잡도 외에 "병목 현상"이 발생할 수 있습니다.
    크기가 반복적으로 증가한다면, 복사가 끝날 때까지 다른 요소 삽입이 지연될 가능성이 있습니다.

<br>

### ArrayList의 내부 동작 원리

**Java ArrayList**

`ArrayList`는 동적으로 크기를 조절할 수 있는 배열 기반의 자료구조임.

내부적으로 `Object[]`을 사용해 데이터를 저장하며, 크기 조절 과정에서 여러 최적화 기법이 사용됨.

<br>

**newCapacity 메서드**
```java
private int newCapacity(int minCapacity) {
    int oldCapacity = elementData.length;
    int newCapacity = oldCapacity + (oldCapacity >> 1); // 현재 capacity 1.5배 증가한 값
    if (newCapacity - minCapacity <= 0) {
        if (elementData == EMPTY_ELEMENT_DATA)
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        if (minCapacity < 0)
            throw new OutOfMemoryError();
        return minCapacity;
    }
    return (newCapacity - MAX_ARRAY_SIZE <= 0)
            ? newCapacity
            : hugeCapcity(minCapacity);
}
```
**grow 메서드**
```java
private Object[] grow() {
    return grow(size + 1);
}

private Object[] grow(int minCapacity) {
    Object[] newElementData = new Object[newCapcity(minCapacity)];
    System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
    return newElementData;
}
```
**add 메서드**
```java
public boolean add(E e) {
    add(e);
    return true;
}

private void add(E e) {
    if (size == elementData.length) {
        elementData = grow();
    }
    // ...
}
```
`add()` 수행마다 `capacity == size`를 체크해서 같으면 용적을 1.5배 증가시킴.

<br>

**System.arraycopy()**
Java에서 배열 복사를 위해 사용하는 네이티브 메서드로,
배열 데이터를 다른 배열로 효율적으로 복사하는 기능을 제공함.

- `메모리 블록 복사` 방식을 사용해 단순한 반복문 복사보다 빠름.
- 네이티브 코드(C/C++)로 구현되어 있음.

Java의 배열은 메모리상 `연속된 공간`에 저장되는데, `메모리 블록 복사`는 데이터를 개별적으로 복사하는 대신,
데이터가 연속적으로 저장된 메모리 블록을 한 번에 복사하는 방식임.