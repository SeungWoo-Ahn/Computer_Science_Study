package Set;

public class HashSet<E> implements Set<E> {
    private final static int DEFAULT_CAPACITY = 1 << 4;
    private final static float LOAD_FACTOR = 0.75f;

    Node<E>[] table;
    private int size;

    public HashSet() {

    }

    /**
     * 보조 해시 함수 (상속 방지를 위한 private static final)
     * 1. key 가 null 일 경우, 0을 반환함
     * 2. 아닐 경우, key 의 hashCode ^ (해시값 >>> 16)
     */
    private static final int hash(Object key) {
        return 0;
    }

    /**
     * 1. 새 용적 2배로 잡음
     * 2. 새 테이블 생성
     * 3. 0부터 차례대로 순회
     * 4. head 가 없을 경우 넘어감
     * 5. 해당 index null 처리, 다음 노드 변수 선언
     * 6. 연결된 노드 모두 순회
     * 7. 새로운 인덱스 구함
     * 8. 새로 담을 인덱스에 노드가 존재하면, 마지막 노드로 가서 연결
     * 9. 비었다면, 해당 노드 추가
     * 10. 기존 테이블 null 처리, 새 테이블로 교체
     */
    private void resize() {

    }

    /**
     * 1. 기존 용적 저장, 새 용적 저장
     * 2. 새 테이블 생성
     * 3. 0부터 차례대로 순회
     * 4. head 가 없을 경우 넘어감, 해당 index null 처리
     * 5. 만약 다음 노드가 없다면 (유일한 노드라면), 새 index 는 해시 & (새 용적 - 1)
     * 6. 만약 두 개 이상의 노드가 연결된다면, 제자리 배치에 사용되는 lowHead, lowTail 및 새롭게 배치하는 highHead, highTail 노드 모두 null 로 선언
     * 7. 다음 노드 next 선언
     * 8. do while 문으로 현재 노드가 null 일때까지 반복
     * 9. next 에 현재 노드의 다음을 할당
     * 10. 현재 노드 해시 & 기존 용적 == 0 경우(몫이 짝수일 경우), low 에 배치됨
     * 11. 처음 배치되면 lowHead 에, 아니면 lowTail 의 next 에 현재 노드 할당, lowTail 에 현재 노드 할당
     * 12. 몫이 홀수일 경우, high 에 배치됨. low 에 배치하는 것과 같은 방식
     * 13. 이후 lowTail 이 존재할 경우, lowTail 의 next 를 끊어주고 새 테이블의 해당 인덱스에 lowHead 배치
     * 15. highTail 이 존재할 경우, highTail 의 next 를 끊어주고 새 테이블의 (해당 인덱스 + 기존 용적)에 highHead 배치
     * 16. 새 테이블로 교체
     */
    private void betterResize() {

    }

    @Override
    public boolean add(E e) {
        return false;
    }

    /**
     * 1. index 를 구한다. index = hash & (table.length - 1)
     * 2. 해당 index 가 비어있을 경우, 새 노드 생성
     * 3. 아닐 경우 (= 해시 충롤), 현재 노드와 이전 노드를 두어 노드를 따라 순회
     * 4. 중간에 해시값과 키 값이 같은 노드를 발견하면, 키를 반환
     * 5. 발견하지 못하면, 마지막에 새 노드 연결
     * 6. 데이터의 개수가 현재 용적의 75%를 넘어가면 resize()
     * 7. 정상적으로 추가되면 null 반환
     */
    private E add(int hash, E key) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    /**
     * 1. 인덱스 저장
     * 2. 현재 노드 저장, removedNode 와 prev 은 null 로 선언
     * 3. 만약 현재 노드 없으면 null 반환
     * 4. 해당 노드 끝까지 순회
     * 5. 같은 노드를 찾았다면, 삭제되는 노드 저장
     * 6. 해당 노드가 헤드라면, 다음 노드를 헤드로 옮김, 현재 노드 null 처리
     * 7. 이외의 경우, 이전 노드와 다음 노드를 연결, 현재 노드 null 처리
     * 8. 사이즈 감소와 탐색 종료
     * 9. removedNode 반환
     */
    private Object remove(int hash, Object key) {
        return null;
    }

    /**
     * 1. 인덱스 저장
     * 2. 헤드 노드 저장
     * 3. 끝까지 탐색하면서 같은 객체를 찾으면 true 반환
     * 4. 아니면 false 반환
     */
    @Override
    public boolean contains(Object o) {
        return false;
    }

    /**
     * 1. 동일한 객체면 true
     * 2. HashSet 가 아니라면 false
     * 3. oSet 선언, try-catch 문으로 obj 를 HashSet 으로 타입 캐스팅
     * 4. 요소 개수가 다르면 false
     * 5. 0번 index 부터 순회함
     * 6. 각 노드 끝까지 탐색하며 contains 로 존재 여부 확인, 아니라면 false 반환
     * 7. 위 과정 모두 통과되면 true 반환
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    /**
     * 1. table 이 null 이 아니고, 사이즈가 0보다 크면, table 모든 노드 삭제
     */
    @Override
    public void clear() {

    }
}
