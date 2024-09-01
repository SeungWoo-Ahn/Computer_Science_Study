package Heap;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class Heap<E> {
    private final Comparator<? super E> comparator;
    private static final int DEFAULT_CAPACITY = 10;

    private int size;

    private Object[] array;

    public Heap() {
        this(null);
    }

    public Heap(Comparator<? super E> comparator) {
        this.array = new Object[DEFAULT_CAPACITY];
        this.comparator = comparator;
        this.size = 0;
    }

    public Heap(int capacity) {
        this(capacity, null);
    }

    public Heap(int capacity, Comparator<? super E> comparator) {
        this.array = new Object[capacity];
        this.comparator = comparator;
        this.size = 0;
    }

    private int getParent(int index) {
        return index / 2;
    }

    private int getLeftChild(int index) {
        return index * 2;
    }

    private int getRightChild(int index) {
        return index * 2 + 1;
    }

    private void resize(int newCapacity) {
        Object[] newArray = new Object[newCapacity];
        for (int i = 1; i <= size; i++) {
            newArray[i] = array[i];
        }
        this.array = null;
        this.array = newArray;
    }

    /**
     * 1. 용적이 꽉 차있을 경우 용적을 두 배로 늘려준다
     * 2. 가장 마지막에 추가 되는 위치와 넣을 값을 siftUp에 넘겨준다
     * 3. 사이즈 증가
     */
    public void add(E e) {
        if (size + 1 == array.length) {
            resize(array.length * 2);
        }
        shiftDown(size + 1, e);
        size++;
    }

    /**
     * 1. comparator 의 존재 여부로 나눠준다
     */
    private void siftUp(int index, E target) {
        if (comparator != null) {
            shiftUpComparator(index, target, comparator);
        } else {
            shiftUpComparable(index, target);
        }
    }

    /**
     * 1. root 노드보다 클 때까지만 탐색한다
     * 2. 삽입 노드의 인덱스 구하기
     * 3. 부모 노드의 값 임시 저장
     * 4. 타겟 노드의 값이 부모 노드보다 크면 반복문 종료
     * 5. 삽입될 위치에 타겟 노드의 값을 저장해준다.
     */
    @SuppressWarnings("unchecked")
    private void shiftUpComparator(int index, E target, Comparator<? super E> comp) {
        while (index > 1) {
            int parentIdx = getParent(index);
            E parentVal = (E) array[parentIdx];
            if (comp.compare(target, parentVal) >= 0) {
                break;
            }
            array[index] = parentVal;
            index = parentIdx;
        }
        array[index] = target;
    }

    /**
     * 1. 타겟 노드가 비교될 수 있도록 comp 변수를 만든다
     * 2. shiftUpComparator 와 동일
     */
    @SuppressWarnings("unchecked")
    private void shiftUpComparable(int index, E target) {
        Comparable<? super E> comp = (Comparable<? super E>) target;
        while (index > 1) {
            int parentIdx = getParent(index);
            E parentVal = (E) array[parentIdx];
            if (comp.compareTo(parentVal) >= 0) {
                break;
            }
            array[index] = parentVal;
            index = parentIdx;
        }
        array[index] = comp;
    }

    /**
     * 1. root 가 비어있으면 예외 던짐
     * 2. 삭제할 요소 데이터 임시 저장
     * 3. 타겟이 될 요소 저장
     * 4. 타겟 노드를 비운다
     * 5. 삭제할 노드의 인덱스와 이후 재배치 할 타겟 노드를 shiftDown 으로 넘겨준다
     * 6. 데이터 반환
     */
    @SuppressWarnings("unchecked")
    public E remove() {
        if (array[1] == null) {
            throw new NoSuchElementException();
        }
        E data = (E) array[1];
        E target;
        if (size == 1) {
            target = null;
        } else {
            target = (E) array[size];
        }
        array[size] = null;
        shiftDown(1, target);
        return data;
    }

    private void shiftDown(int index, E target) {
        if (comparator != null) {
            shiftDownComparator(index, target, comparator);
        } else {
            shiftUpComparable(index, target);
        }
    }

    /**
     * 1. 삭제할 인덱스의 노드를 삭제
     * 2. 사이즈 줄임
     * 3. 삭제할 노드부터 시작할 부모를 가리키는 변수 저장
     * 4. 교환될 자식을 가르키는 변수 선언
     * 5. 왼쪽 자식 노드의 인덱스가 요소의 개수보다 작거나 같을 때까지 반복
     * 6. 오른쪽 자식 인덱스 저장
     * 7. 왼쪽 자식의 값 임시 저장
     * 8. 오른쪽 자식 인덱스가 size 를 넘지 않으면서, 왼쪽 자식이 오른쪽 자식보다 큰 경우
     * 9. 자식 변수와 자식 값을 오른쪽 자식으로 바꿔준다
     * 10. 재배치할 노드가 자식 노드보다 작을경우 반복문을 종료한다
     * 11. 재배치되는 위치에 타겟이 된 값을 넣어준다
     * 12. 용적 사이즈가 최소 용적보다 크면서 요소의 개수가 용적의 1/4일 때, 용적을 반으로 줄임
     */
    @SuppressWarnings("unchecked")
    private void shiftDownComparator(int idx, E target, Comparator<? super E> comp) {
        array[idx] = null;
        size--;
        int parent = idx;
        int child;
        while ((child = getLeftChild(parent)) <= size) {
            int rightIdx = getRightChild(parent);
            E childVal = (E) array[child];
            if (rightIdx <= size && comp.compare(childVal, (E) array[rightIdx]) > 0) {
                child = rightIdx;
                childVal = (E) array[child];
            }
            if (comp.compare(target, childVal) <= 0) {
                break;
            }
            array[parent] = childVal;
            parent = child;
        }
        array[parent] = target;
        if (array.length > DEFAULT_CAPACITY && size < array.length / 4) {
            resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
        }
    }

    @SuppressWarnings("unchecked")
    private void shitDownComparable(int index, E target) {
        Comparable<? super E> comp = (Comparable<? super E>) target;
        array[index] = null;
        size--;
        int parent = index;
        int child;
        while ((child = getLeftChild(parent)) <= size) {
            int rightIdx = getRightChild(parent);
            E childVal = (E) array[child];
            if (rightIdx <= size && ((Comparable<? super E>) childVal).compareTo((E) array[rightIdx]) > 0) {
                child = rightIdx;
                childVal = (E) array[rightIdx];
            }
            if (comp.compareTo(childVal) <= 0) {
                break;
            }
            array[parent] = childVal;
            parent = child;
        }
        array[parent] = comp;
        if (array.length > DEFAULT_CAPACITY && size < array.length / 4) {
            resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
        }
    }

    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    public E peek() {
        if (array[1] == null) {
            throw new NoSuchElementException();
        }
        return (E) array[1];
    }

    public boolean insEmpty() {
        return size == 0;
    }
}
