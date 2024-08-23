package Queue;

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

    }

    public CircularQueue(int capacity) {

    }

    /**
     * 1. 새 용량을 갖는 배열을 생성
     * 2. i = new array index, j = original array index 로 새 배열에 값 복사
     * 3. 새 배열을 기존 array 에 덮어씌움
     * 4. front, rear 다시 설정
     */
    private void resize(int newCapacity) {

    }

    /**
     * 1. 용량이 가득 찾을 경우, 용량을 두 배로 늘려줌
     * 2. rear 를 한 칸 옮김
     * 3. rear 에 요소 삽입 및 사이즈 증가
     */
    @Override
    public boolean offer(E e) {
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
    public E poll() {
        return null;
    }

    /**
     * 1. poll() 한 데이터를 임시 저장
     * 2. 데이터가 null 이라면 NoSuchElementException
     * 3. 데이터 반환
     */
    public E remove() {
        return null;
    }

    /**
     * 1. 요소가 없다면 null 반환
     * 2. 맨 앞 요소를 반환
     */
    @Override
    public E peek() {
        return null;
    }

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }

    /**
     * 1. 요소 처음 위치 저장
     * 2. i: 요소 개수만큼 반복, idx: 원소의 위치로, 매 회 (idx + 1) % array.length 의 위치로 갱신
     */
    public boolean contains(Object o) {
        return false;
    }

    /**
     * 1. 모든 공간을 명시적으로 null 처리
     * 2. front, rear, size 초기화
     */
    public void clear() {

    }
}
