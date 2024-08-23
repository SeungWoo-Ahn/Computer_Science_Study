package Stack;

import java.util.Arrays;
import java.util.EmptyStackException;

public class MStack<E> implements StackInterface<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ARRAY = {};

    private Object[] array;
    private int size;

    public MStack() {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public MStack(int capacity) {
        this.array = new Object[capacity];
        this.size = 0;
    }

    /*
    * 1. 빈 배열일 경우
    * 2. 가득찰 경우
    * 3. 사이즈가 용량 절반 이하일 경우
    * */
    private void resize() {
        if (Arrays.equals(array, EMPTY_ARRAY)) {
            array = new Object[DEFAULT_CAPACITY];
            return;
        }
        int array_length = array.length;
        if (size == array_length) {
            int doubled_length = array_length * 2;
            array = Arrays.copyOf(array, doubled_length);
            return;
        }
        if (size < array_length / 2) {
            int half_length = array_length / 2;
            array = Arrays.copyOf(array, Math.max(half_length, DEFAULT_CAPACITY));
        }
    }

    /*
    * 1. 용량이 꽉 찼다면, resize() 호출
    * 2. 마지막 위치에 요소 추가
    * */
    @Override
    public E push(E e) {
        if (size == array.length) {
            resize();
        }
        array[size] = e;
        size++;
        return e;
    }

    /*
    * 1. 삭제할 요소가 없다면, EmptyStackException
    * 2. 삭제할 요소 임시 저장
    * 3. 요소 삭제
    * 4. 사이즈 감소
    * 5. resize()
    * */
    @Override
    @SuppressWarnings("unchecked")
    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        E data = (E) array[size - 1];
        array[size - 1] = null;
        size--;
        resize();
        return data;
    }

    /*
    * 1. 삭제할 요소가 없다면, EmptyStackException
    * 2. 마지막 요소 반환
    * */
    @Override
    @SuppressWarnings("unchecked")
    public E peek() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return (E) array[size - 1];
    }

    /*
    * 스택의 Top 에서부터 찾아서 일치하는 첫 번째 요소가 Top 에서 몇 번째에 있는지 반환
    * Top 부터 1로 셈
    * 1. 찾으려는 값이 null 일때
    * 2. 찾으려는 값이 null 이 아닐때
    * */
    @Override
    public int search(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (array[i] == null) {
                    return size - i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (array[i].equals(o)) {
                    return size - i;
                }
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    /*
    * 1. 저장된 데이터 모두 null 처리
    * 2. 사이즈 0
    * 3. resize() 로 현재 용적을 절반으로 줄임
    * */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
        resize();
    }

    @Override
    public boolean empty() {
        return size == 0;
    }
}
