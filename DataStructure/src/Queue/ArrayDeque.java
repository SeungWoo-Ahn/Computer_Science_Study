package Queue;

public class ArrayDeque<E> implements Queue<E> {

    private static final int DEFAULT_CAPACITY = 64;

    private Object[] array;
    private int size;

    private int front;
    private int rear;

    public ArrayDeque() {

    }

    public ArrayDeque(int capacity) {

    }

    /**
     * 1. 용적을 변경한 새 배열 생성
     * 2. i = 새 배열 index, j = 기존 배열 index 로 size 만큼 복사
     * 3. 기존 배열 삭제
     * 4. 새 배열 덮어쓰기
     * 5. front, rear 변경
     */
    private void resize(int newCapacity) {

    }

    /**
     * offerLast 호출
     */
    @Override
    public boolean offer(E e) {
        return false;
    }

    /**
     * 1. 용적이 가득 찼을 경우, 두 배로 늘려줌
     * 2. rear 을 다음 위치로 갱신
     * 3. 데이터 삽입 및 사이즈 증가
     */
    public boolean offerLast(E e) {
        return false;
    }

    /**
     * 1. 용적이 가득 찼을 경우, 두 배로 늘려줌
     * 2. 데이터 삽입
     * 3. front 갱신
     * 4. 사이즈 증가
     */
    public boolean offerFirst(E e) {
        return false;
    }

    /**
     * pollFirst 호출
     */
    @Override
    public E poll() {
        return null;
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
    public E pollFirst() {
        return null;
    }

    /**
     * removeFirst 호출
     */
    public E remove() {
        return null;
    }

    /**
     * 1. pollFirst 호출
     * 2. 없으면 예외 던지기
     */
    public E removeFirst() {
        return null;
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
    public E pollLast() {
        return null;
    }

    /**
     * 1. pollLast 호출
     * 2. 없으면 예외 던지기
     */
    public E removeLast() {
        return null;
    }

    /**
     * 1. peekFirst 호출
     */
    @Override
    public E peek() {
        return null;
    }

    /**
     * 1. 요소가 없으면 null 반환
     */
    public E peekFirst() {
        return null;
    }

    /**
     * 1. 요소가 없으면 null 반환
     */
    public E peekLast() {
        return null;
    }

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }

    /**
     * 1. i: 요소 개수만큼, idx: 원소 위치로 순회하며 찾음
     */
    public boolean contains(Object o) {
        return false;
    }

    /**
     * 1. 전체 배열 삭제
     * 2. front, rear, size 초기화
     */
    public void clear() {

    }
}
