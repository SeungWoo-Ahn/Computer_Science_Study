package Tree;

import java.util.Comparator;

public class BinarySearchTree<E> {
    private Node<E> root;
    private int size;
    private final Comparator<? super E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<? super E> comparator) {
        this.root = null;
        this.size = 0;
        this.comparator = comparator;
    }

    public boolean add(E value) {
        return false;
    }

    /**
     * 1. 탐색할 노드를 가리킬 current를 root로 선언
     * 2. root가 null이면, 새 노드를 만들고 null 반환
     * 3. 직전 탐색 노드 currentParent 선언
     * 4. Comparable comp 선언
     * 5. 비교 결과 compResult (+, 0, -) 선언
     * 6. current가 null이 아닐 때까지 반복
     * 7. compResult가 0보다 작으면 left로 이동, 0보다 크면 right로 이동
     * 8. 같은 값이면 value return
     * 9. 새 노드 생성, compResult에 따라 연결
     * 10. 사이즈 증가 후 null 반환
     */
    private E addUsingComparable(E value) {
        return null;
    }

    private E addUsingComparator(E value, Comparator<? super E> comp) {
        return null;
    }

    public E remove(Object o) {
        return null;
    }

    /**
     * 1. 삭제하려는 값을 E oldVal로 선언
     * 2. 부모 노드 null로 선언, 현재 노드 root로 선언
     * 3. 삭제하고자 하는 노드가 왼쪽 자식인지, 오른쪽 자식인지 판별하는 hasLeft 변수 선언
     * 4. root 가 없으면 null 반환
     * 5. value에 해당하는 Comparator 선언
     * 6. 현재 노드가 null이 아닐 때까지 반복, 삭제할 노드를 찾으면 break
     * 7. 삭제할 노드를 찾지 못했다면 null 반환
     * 8. 부모 노드가 없을 경우, 삭제할 노드가 root 노드임, 처리 후 oldVal 반환
     * 9. 삭제할 노드가 왼쪽 자식인지 오른쪽 자식인지 나눠서 처리
     * 10. 사이즈 감소 후 oldVal 반환
     */
    private E removeUsingComparable(Object value) {
        return null;
    }

    private E removeUsingComparator(Object value, Comparator<? super E> comp) {
        return null;
    }

    /**
     * 대체 노드(successor)를 찾을 것임 (오른쪽 서브 트리 중 가장 작은 값의 노드)
     * 1. 부모 노드를 가리킬 currentParent를 node로 선언
     * 2. 현재 노드를 가리킬 current를 오른쪽 node로 선언
     * 3. current의 왼쪽 자식이 없는 경우, 오른쪽 첫 자식이 대체 노드가 되는 것 -> currentParent과 연결해줌
     * 4. current의 오른쪽 자식을 gc, current를 반환함.
     * 5. 가장 작은 노드를 찾을 때까지 왼쪽 자식 노드를 따라 이동함.
     * 6. 부모의 left에 대체 노드의 오른쪽 자식을 연결함.
     * 7. current의 오른쪽 자식을 gc, current를 반환함.
     */
    private Node<E> getSuccessorAndUnlink(Node<E> node) {
        return null;
    }

    /**
     * 1. node가 null이 아닐 경우 진행
     * 2. 자식 노드가 없을 경우 처리
     * 3. 양쪽 자식 노드가 있는 경우 처리, 대체 노드를 찾아서 삭제된 노드 값을 대체 노드 값으로 교체함.
     * 4. 왼쪽 노드만 존재할 경우 처리
     * 5. 오른쪽 노드만 존재할 경우 처리
     * 6. node를 반환
     */
    private Node<E> deleteNode(Node<E> node) {
        return null;
    }

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean contains(Object o) {
        return false;
    }

    /**
     * 1. value에 해당하는 Comparator 선언
     * 2. 현재 노드를 root로 선언
     * 3. 현재 노드가 null이 아닐 때까지 반복하며 탐색
     */
    private boolean containsUsingComparable(Object o) {
        return false;
    }

    private boolean containsUsingComparator(Object o, Comparator<? super E> comp) {
        return false;
    }

    public void clear() {

    }
}
