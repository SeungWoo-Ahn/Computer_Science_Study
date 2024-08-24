package Queue;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * CircularQueue 즉 원형 큐는 Array 와 front, rear 변수를 활용해서 만듦
 * front 의 다음 인덱스부터 값이 들어가있는데, 왜 front 부터 값을 넣지 않을까?
 * 만약 연속해서 poll() 하다가 마지막 요소까지 삭제한 경우, front 와 rear 의 위치가 엇갈리게 된다
 * 원형 큐가 비었는지 가득차있는지는 두 변수만으로는 알 수 없기 때문에 front 를 빈공간으로 만들어준 것
 */
public class CircularQueue<E> implements Queue<E> {
    private static final int DEFAULT_CAPACITY = 64;

    private Object[] array;
    private int size;

    private int front; // 시작 인덱스를 가리키는 변수 (빈 공간임을 유의)
    private int rear; // 마지막 요소의 인덱스를 가리키는 변수

    public CircularQueue() {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    public CircularQueue(int capacity) {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    /**
     * 1. 새 용량을 갖는 배열을 생성
     * 2. i = new array index, j = original array index 로 새 배열에 값 복사
     * 3. 새 배열을 기존 array 에 덮어씌움
     * 4. front, rear 다시 설정
     */
    private void resize(int newCapacity) {
        int originCapacity = array.length;
        Object[] new_array = new Object[newCapacity];
        for (int i = 1, j = front + 1; i <= size; i++, j++) {
            new_array[i] = array[j % originCapacity];
        }
        this.array = null;
        this.array = new_array;
        front = 0;
        rear = size;
    }

    /**
     * 1. 용량이 가득 찾을 경우, 용량을 두 배로 늘려줌
     * 2. rear 를 한 칸 옮김
     * 3. rear 에 요소 삽입 및 사이즈 증가
     */
    @Override
    public boolean offer(E e) {
        if ((rear + 1) % array.length == front) {
            resize(array.length * 2);
        }
        rear = (rear + 1) % array.length;
        array[rear] = e;
        size++;
        return false;
    }

    /**
     * 1. 삭제할 요소가 없을 경우, null 반환
     * 2. front 를 한 칸 옮김
     * 3. 반환할 데이터를 임시 저장
     * 4. 해당 front 의 데이터 삭제
     * 5. 사이즈 감소
     * 6. 용량이 최소 크기보다 크고, 요소 개수가 용량의 1/4 미만일 경우,
     * 최소 용량과 현재 용량 절반 중 큰 값으로 resize()
     * 7. 삭제된 데이터 반환
     */
    @Override
    @SuppressWarnings("unchecked")
    public E poll() {
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
     * 1. poll() 한 데이터를 임시 저장
     * 2. 데이터가 null 이라면 NoSuchElementException
     * 3. 데이터 반환
     */
    public E remove() {
        E data = poll();
        if (data == null) {
            throw new NoSuchElementException();
        }
        return data;
    }

    /**
     * 1. 요소가 없다면 null 반환
     * 2. 맨 앞 요소를 반환
     */
    @Override
    @SuppressWarnings("unchecked")
    public E peek() {
        if (size == 0) {
            return null;
        }
        return (E) array[(front + 1) % array.length];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 1. 요소 처음 위치 저장
     * 2. i: 요소 개수만큼 반복, idx: 원소의 위치로, 매 회 (idx + 1) % array.length 의 위치로 갱신
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
     * 1. 모든 공간을 명시적으로 null 처리
     * 2. front, rear, size 초기화
     */
    public void clear() {
        Arrays.fill(array, null);
        front = 0;
        rear = 0;
        size = 0;
    }
}
