package Set;

public class LinkedHashSet<E> implements Set<E> {
    private static class Node<E> {
        final int hash;
        final E key;
        Node<E> next; // for Separate Chaining
        Node<E> nextLink; // for linked list (set)
        Node<E> prevLink; // for linked list (set)

        public Node(int hash, E key, Node<E> next) {
            this.hash = hash;
            this.key = key;
            this.next = next;
            this.nextLink = null;
            this.prevLink = null;
        }
    }

    private final static int DEFAULT_CAPACITY = 1 << 4;
    private static final float LOAD_FACTOR = 0.75f;

    private Node<E>[] table;
    private int size;

    // 들어온 순서를 유지할 linkedList
    private Node<E> head;
    private Node<E> tail;

    public LinkedHashSet() {

    }

    private static final int hash(Object key) {
        return 0;
    }

    /**
     * 1. 새 용적을 table 길이의 2배로 선언
     * 2. 새 용적을 가지는 새 테이블 생성
     * 3. 0번째 index 부터 테이블을 순회함, 해당 값이 없으면 넘어감
     * 4. 해당 index null 처리
     * 5. value 가 null이 아닐 때까지 nextNode와 함께 연결된 노드를 순회함
     * 6. 새로운 index 를 해시값 % newCapacity로 구함
     * 7. 해당 index에서 충돌한다면 가장 마지막 노드로 이동 후 연결해줌
     * 8. 아니라면 해당 value의 next를 끊어주고 테이블에 삽입
     * 9. 기존 테이블은 새 테이블로 교체
     */
    private void resize() {

    }

    /**
     * 1. 마지막 노드를 가져옴
     * 2. tail이 새 노드를 가리키도록 갱신
     * 3. 마지막 노드가 null이면, 데이터가 없는 것이므로 head도 갱신
     * 4. 아니라면 기존 tail과 새 노드를 연결
     */
    private void linkLastNode(Node<E> node) {

    }

    @Override
    public boolean add(E e) {
        return false;
    }

    /**
     * 1. hash 값으로 idx 값을 얻음
     * 2. 새로운 노드를 생성
     * 3. 해당 idx가 비었다면 바로 삽입
     * 4. 아니라면 마지막 체인 노드까지 탐색
     * 5. 탐색 중 동일 객체라면 key 를 반환, 동일 객체는 해시값과 key값의 동일성으로 판단
     * 6. 사이즈 증가
     * 7. 해당 노드를 linkedList에 연결
     * 8. 데이터 개수가 table 용적 75%를 넘어가면 resize
     * 9.
     */
    private E add(int hash, E key) {
        return null;
    }

    /**
     * 1. 삭제하는 노드의 이전/이후 노드를 저장
     * 2. 이전 노드가 null이라며 head 노드였다는 것을 의미 -> 이후 노드가 head가 됨
     * 3. 아니라면 이전 노드의 다음 노드를 이후 노드로 연결해줌 + gc
     * 4. 이후 노드가 null이라면 tail 노드였다는 것을 의미 -> 이전 노드가 tail이 됨
     * 5. 아니라면 이후 노드의 앞 노드를 이전 노드로 연결해줌 + gc
     */
    private void unlinkNode(Node<E> node) {

    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    /**
     * 1. hash값에 해당하는 index를 구함
     * 2. 해당 노드 선언, 삭제된 노드 및 앞 노드는 null로 선언
     * 3. 해당 노드가 이미 없다면 null 반환
     * 4. 현재 노드가 null 일 때까지 반복
     * 5. hash값과 key값으로 동일성을 비교해서 같은 노드를 찾음
     * 6. 찾았다면 삭제된 노드에 담아둠
     * 7. 테이블의 첫 번째 체인 노드일 경우, 해당 인덱스의 테이블을 다음 것으로 갱신
     * 8. 아니라면 이전 노드와 다음 노드를 연결해줌
     * 9. linkedList를 끊고, gc 후 사이즈를 감소시키며 break
     * 10. 저장한 삭제된 노드를 반환
     */
    private Object remove(int hash, Object key) {
        return null;
    }

    @Override
    public boolean contains(Object o) {
        return false;
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
     * 1. 테이블이 null이 아니고 size가 0 이상이면, 테이블을 순회하며 gc 처리, size 초기화
     * 2. head와 tail gc 처리
     */
    @Override
    public void clear() {

    }
}