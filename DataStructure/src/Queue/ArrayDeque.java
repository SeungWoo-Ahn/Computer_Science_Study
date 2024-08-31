package Queue;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayDeque<E> implements Queue<E> {

    private static final int DEFAULT_CAPACITY = 64;

    private Object[] array;
    private int size;

    private int front;
    private int rear;

    public ArrayDeque() {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    public ArrayDeque(int capacity) {
        this.array = new Object[capacity];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    /**
     * 1. 용적을 변경한 새 배열 생성
     * 2. i = 새 배열 index, j = 기존 배열 index 로 size 만큼 복사
     * 3. 기존 배열 삭제
     * 4. 새 배열 덮어쓰기
     * 5. front, rear 변경
     */
    private void resize(int newCapacity) {
        Object[] newArray = new Object[newCapacity];
        for (int i = 1, j = front + 1; i <= size; i++, j++) {
            newArray[i] = array[j % array.length];
        }
        this.array = null;
        this.array = newArray;
        front = 0;
        rear = size;
    }

    /**
     * offerLast 호출
     */
    @Override
    public boolean offer(E e) {
        return offerLast(e);
    }

    /**
     * 1. 용적이 가득 찼을 경우, 두 배로 늘려줌
     * 2. rear 을 다음 위치로 갱신
     * 3. 데이터 삽입 및 사이즈 증가
     */
    public boolean offerLast(E e) {
        if ((rear + 1) % array.length == front) {
            resize(array.length * 2);
        }
        rear = (rear + 1) % array.length;
        array[rear] = e;
        size++;
        return false;
    }

    /**
     * 1. 용적이 가득 찼을 경우, 두 배로 늘려줌
     * 2. 데이터 삽입
     * 3. front 갱신
     * 4. 사이즈 증가
     */
    public boolean offerFirst(E e) {
        if ((front - 1 + array.length) % array.length == rear) {
            resize(array.length * 2);
        }
        array[front] = e;
        front = (front - 1 + array.length) % array.length;
        size++;
        return false;
    }

    /**
     * pollFirst 호출
     */
    @Override
    public E poll() {
        return pollFirst();
    }

    /**
     * 1. 삭제할 요소가 없으면 null 반환
     * 2. front 위치 갱신
     * 3. 데이터 임시 저장
     * 4. front 데이터 삭제
     * 5. 사이즈 감소
     * 6. 용적이 최소 크기보다 크고 요소 개수가 1/4 미만일 경우
     * 7. 최소 크기와 현재 크기 절반 중 큰 것으로 resize
     */
    @SuppressWarnings("unchecked")
    public E pollFirst() {
        if (size == 0) {
            return null;
        }
        front = (front + 1) % array.length;
        E data = (E) array[front];
        array[front] = null;
        size--;
        if (array.length > DEFAULT_CAPACITY && size < array.length / 4) {
            resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
        }
        return data;
    }

    /**
     * removeFirst 호출
     */
    public E remove() {
        return removeFirst();
    }

    /**
     * 1. pollFirst 호출
     * 2. 없으면 예외 던지기
     */
    public E removeFirst() {
        E data = pollFirst();
        if (data == null) {
            throw new NoSuchElementException();
        }
        return data;
    }

    /**
     * 1. 삭제할 요소 없으면 null 반환
     * 2. 데이터 임시 저장
     * 3. rear 데이터 삭제
     * 4. rear 한 칸 옮기기
     * 5. 사이즈 감소
     * 6. 용적이 최소 크기보다 크고 요소 개수가 1/4 미만일 경우
     * 7. 최소 크기와 현재 크기 절반 중 큰 것으로 resize
     */
    @SuppressWarnings("unchecked")
    public E pollLast() {
        if (size == 0) {
            return null;
        }
        E data = (E) array[rear];
        array[rear] = null;
        rear = (rear - 1 + array.length) % array.length;
        size--;
        if (array.length > DEFAULT_CAPACITY && size < array.length / 4) {
            resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
        }
        return data;
    }

    /**
     * 1. pollLast 호출
     * 2. 없으면 예외 던지기
     */
    public E removeLast() {
        return pollLast();
    }

    /**
     * 1. peekFirst 호출
     */
    @Override
    public E peek() {
        return peekFirst();
    }

    /**
     * 1. 요소가 없으면 null 반환
     */
    @SuppressWarnings("unchecked")
    public E peekFirst() {
        if (size == 0) {
            return null;
        }
        return (E) array[(front + 1) % array.length];
    }

    /**
     * 1. 요소가 없으면 null 반환
     */
    @SuppressWarnings("unchecked")
    public E peekLast() {
        if (size == 0) {
            return null;
        }
        return (E) array[rear];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 1. i: 요소 개수만큼, idx: 원소 위치로 순회하며 찾음
     */
    public boolean contains(Object o) {
        int start = (front + 1) % array.length;
        for (int i = 0, idx = start; i < size; i++, idx = (idx + 1) % array.length) {
            if (Objects.equals(array[idx], o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 1. 전체 배열 삭제
     * 2. front, rear, size 초기화
     */
    public void clear() {
        Arrays.fill(array, null);
        front = rear = size = 0;
    }
}
