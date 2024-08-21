import java.util.Arrays;

interface MList<E> {
    boolean add(E e);
    void add(int index, E e);

    boolean remove(Object o);
    E remove(int index);

    E get(int index);
    void set(int index, E e);

    boolean contains(Object o);
    int indexOf(Object o);
    int lastIndexOf(Object o);

    int size();
    boolean isEmpty();

    void clear();
}

public class MArrayList<E> implements MList<E> {

    private static final int DEFAULT_CAPACITY = 5; //기본 용량
    private static final Object[] EMPTY_ELEMENT_DATA = {}; // 빈 배열

    private int size; // elementData 배열의 총 개수
    Object[] elementData; // 자료를 담을 배열

    /*
    * 생성자 (초기 공간 할당 X)
    * 디폴트 용량으로 초기화
    * */
    public MArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /*
    * 생성자 (초기 공간 할당 O)
    * capacity 양수일 경우 해당 용량으로 배열 생성
    * capacity 0일 경우 디폴트 용량으로 초기화
    * capacity 음수일 경우 RuntimeException 의 IllegalAccessException 주기
    * */
    public MArrayList(int capacity) {
        if (capacity > 0) {
            elementData = new Object[capacity];
        } else if (capacity == 0) {
            elementData = new Object[DEFAULT_CAPACITY];
        } else {
            throw new RuntimeException(new IllegalAccessException());
        }
        size = 0;
    }

    /*
    * ArrayList 는 가변 배열이기 때문에 용량을 재설정함
    * */
    private void resize() {
        int element_capacity = elementData.length;

        /*
        * 용량이 꽉찬 경우
        * 1. 현재 용량의 두 배로 설정
        * 2. 복사할 배열을 두 배로 설정한 용량만큼 설정하고, elementData 원소들을 전체 복사해넣고 반환
        * */
        if (element_capacity == size) {
            int doubled_capacity = element_capacity * 2;
            elementData = Arrays.copyOf(elementData, doubled_capacity);
            return;
        }

        /*
        * 용량에 비해 데이터가 적은 경우
        * 1. 개수가 용량 절반보다 적다면, 절반 용량을 만듦
        * 2. 절반 용량과 디폴트 용량 중 큰 값을 용량으로 설정해 배열을 복사한 후 반환
        * */
        if ((element_capacity / 2) > size) {
            int half_capacity = element_capacity / 2;
            elementData = Arrays.copyOf(elementData, Math.max(half_capacity, DEFAULT_CAPACITY));
            return;
        }

        /*
        * 들어있는 데이터가 하나도 없을 경우
        * 1. 현재 빈 배열일 경우, 다시 기본 용량으로 초기화 후 반환
        * */
        if (Arrays.equals(elementData, EMPTY_ELEMENT_DATA)) {
            elementData = new Object[DEFAULT_CAPACITY];
        }
    }

    /*
    * 가장 마지막 부분에 데이터 삽입
    * 먼저 resize()를 호출 후 마지막에 데이터 삽입
    * */
    @Override
    public boolean add(E e) {
        resize();
        elementData[size] = e;
        size++;
        return true;
    }

    /*
    * 배열 중간에 데이터를 삽입
    * */
    @Override
    public void add(int index, E e) {
        /*
        * 인덱스가 음수이거나, 배열 크기를 벗어난 경우
        * IndexOutOfBoundsException 발생
        * */
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        /*
        * 인덱스가 마지막 위치일 경우, 그냥 추가함
        * 인덱스가 중간 위치일 경우
        * 1. 먼저 resize() 호출
        * 2. 맨 뒤에서부터 index 바로 뒤까지 한 칸씩 뒤로 밀어 빈 공간 만들기
        * 3. index 에 해당 데이터 삽입함
        * */
        if (index == size) {
            add(e);
        } else {
            resize();
            for (int i = size; i > index; i--) {
                elementData[i] = elementData[i - 1];
            }
            elementData[index] = e;
            size++;
        }
    }

    /*
    * 특정 값 중 가장 처음 해당하는 요소를 제거함
    * 1. 해당 값이 몇 번째 위치에 존재하는지 인덱스를 얻어옴
    * 2. 인덱스가 -1이면 삭제하려는 값이 없는 것이니 그대로 메서드를 종료함
    * 3. 인텍스를 찾았으면 remove(int index)로 넘겨 삭제함
    * */
    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1) return false;
        remove(index);
        return true;
    }

    /*
    * 특정 index 에 있는 값을 삭제함
    * 1. 인덱스가 음수거나, size 보다 같거나 클 경우, IndexOutOFBoundsException 발생
    * 2. 반환할 값 백업
    * 3. 요소 null 처리로 제거 (명시적으로 null 처리를 해야 GC가 수거해감)
    * 4. 삭제한 요소의 뒤에 있는 모든 요소들을 한 칸씩 당겨옴
    * 5. 요소 제거했으니 size 감소함
    * 6. capacity 가 남아돌 수 있으니 resize() 호출
    * 7. 백업한 삭제된 요소를 반환
    * */
    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E element = (E) elementData[index];
        elementData[index] = null;
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
            elementData[i + 1] = null;
        }
        size--;
        resize();
        return element;
    }

    /*
    * index 에 저장된 요소를 가져옴
    * 1. index 범위 체크
    * 2. 요소값 반환 (형변환 필요)
    * */
    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) elementData[index];
    }

    /*
    * index 에 요소를 덮어씀
    * 1. index 범위 체크
    * 2. 요소값 덮어쓰기
    * */
    @Override
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        elementData[index] = e;
    }

    /*
    * 찾고자 하는 요소가 포함되었다면 true, 아니라면 false 반환
    * 요소의 인덱스값이 0보다 크면 true, 아니면 false
    * */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /*
    * 순차적으로 검색해서 해당 값이 있는 위치를 반환
    * */
    @Override
    public int indexOf(Object o) {
        if (o == null) { // 매개변수가 null 일 경우 (ArrayList 는 null 도 저장 가능하기 때문에)
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else { // 매개변수가 실질적인 값일 경우
            for (int i = 0; i < size; i++) {
                if (elementData[i].equals(o)) {
                    return i;
                }
            }
        }
        // 찾는 값이 없는 경우 -1 반환
        return -1;
    }

    /*
    * 거꾸로 검색해서 해당 값이 있는 위치를 반환
    * */
    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (elementData[i].equals(o)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /*
    * 모든 요소 지우기
    * 1. 기본 용량으로 초기화
    * 2. size 도 초기화
    * */
    @Override
    public void clear() {
        elementData = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }
}

class MArrayListTest {
    public static void main(String[] args) {
        MArrayList<Integer> list = new MArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1, 4);
        list.add(5);
        list.add(3, 6);
        System.out.println(list);

        list.remove(1);
        list.remove(Integer.valueOf(5));
        System.out.println(list);

        list.set(1, 7);
        System.out.println(list);
        System.out.println(list.get(0));
        System.out.println(list.contains(3));
        System.out.println(list.lastIndexOf(6));
    }
}



