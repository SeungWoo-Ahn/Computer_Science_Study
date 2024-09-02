package PriorityQueue;

import Queue.Queue;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class PriorityQueue<E> implements Queue<E> {
    private final Comparator<? super E> comparator;
    private static final int DEFAULT_CAPACITY = 10;

    private Object[] array;
    private int size;

    public PriorityQueue() {
        this(null);
    }

    public PriorityQueue(Comparator<? super E> comparator) {
        this.array = new Object[DEFAULT_CAPACITY];
        this.comparator = comparator;
        this.size = 0;
    }

    public PriorityQueue(int capacity) {
        this(capacity, null);
    }

    public PriorityQueue(int capacity, Comparator<? super E> comparator) {
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

    @Override
    public boolean offer(E e) {
        if (size + 1 == array.length) {
            resize(array.length * 2);
        }
        shiftUp(size + 1, e);
        size++;
        return true;
    }

    private void shiftUp(int index, E target) {
        if (comparator != null) {
            shiftUpComparator(index, target, comparator);
        } else {
            shiftUpComparable(index, target);
        }
    }

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
     * 1. root 가 null 이면, null 반환
     * 2. 아니면 remove() 반환
     */
    @Override
    public E poll() {
        if (array[1] == null) {
            return null;
        }
        return remove();
    }

    /**
     * 1. root 가 null 이면, 예외 던짐
     * 2. 삭제될 데이터 임시 저장
     * 3. 타겟 요소 사이즈에 따라 저장
     * 4. 타겟 노드 비움
     * 5. 사이즈 감소
     * 6. shiftDown
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
        size--;
        shiftDown(1, target);
        return data;
    }

    private void shiftDown(int index, E target) {
        if (comparator != null) {
            shiftDownComparator(index, target, comparator);
        } else {
            shiftDownComparable(index, target);
        }
    }

    @SuppressWarnings("unchecked")
    private void shiftDownComparator(int index, E target, Comparator<? super E> comp) {
        array[index] = null;
        int parent = index;
        int child;
        while ((child = getLeftChild(parent)) <= size) {
            int right = getRightChild(parent);
            E childVal = (E) array[child];
            if (right <= size && comp.compare(childVal, (E) array[right]) > 0) {
                child = right;
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
    private void shiftDownComparable(int index, E target) {
        Comparable<? super E> comp = (Comparable<? super E>) target;
        array[index] = null;
        int parent = index;
        int child;
        while ((child = getLeftChild(parent)) <= size) {
            int right = getRightChild(parent);
            E childVal = (E) array[child];
            if (right <= size && ((Comparable<? super E>) childVal).compareTo((E) array[right]) > 0) {
                child = right;
                childVal = (E) array[child];
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

    @SuppressWarnings("unchecked")
    @Override
    public E peek() {
        if (array[1] == null) {
            throw new NoSuchElementException();
        }
        return (E) array[1];
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        for (int i = 1; i <= size; i++) {
            if (Objects.equals(array[i], o)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        for (int i = 1; i <= size; i++) {
            array[i] = null;
        }
        size = 0;
    }
}
