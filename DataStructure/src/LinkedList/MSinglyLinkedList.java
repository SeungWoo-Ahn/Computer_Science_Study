package LinkedList;

import java.util.Arrays;

public class MSinglyLinkedList<E> {
    private Node<E> head;
    private Node<E> tail;

    private int size;

    /*
    * 생성자
    * 모든 값 초기화
    * */
    public MSinglyLinkedList() {
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    /*
    * LinkedList 의 index 번째 있는 요소를 반환
    * */
    private Node<E> search(int index) {
        return null;
    }

    /*
    * 맨 앞에 요소를 추가함
    * 1. 가장 앞의 요소를 가져옴
    * 2. 새 노드를 생성
    * 3. 사이즈 늘림
    * 4. head 를 업데이트함
    * 만일 최초로 요소가 add 된 것이면 head 와 tail 이 같게 됨
    * */
    public void addFirst(E e) {

    }

    /*
    * 맨 뒤에 요소를 추가함
    * 1. 가장 뒤의 요소를 가져옴
    * 2. 새 노드를 생성
    * 3. 사이즈 늘림
    * 4. tail 을 업데이트함
    * 5. 만일 최초로 add 된 것이면 head 와 tail 이 같게 됨
    * 6. 아니라면 기존 마지막 변수가 새 노드를 가리키게 함
    * */
    public void addLast(E e) {

    }

    /*
    * 맨 뒤에 요소를 추가함
    * 1. addLast() 와 같음
    * */
    public boolean add(E e) {
        return true;
    }

    /*
    * 리스트 중간에 요소를 추가함
    * 1. 범위 체크, 불가능하면 exception
    * 2. index 가 0이면 addFirst() 후 반환
    * 3. index 가 마지막이면 addLast() 후 반환
    * 4. 추가하려는 위치의 이전 노드를 얻음
    * 5. 추가하려는 위치의 다음 노드를 얻음
    * 6. 새 노드 생성
    * 7. 사이즈 증가
    * 8. 이전 노드와 새 노드를 연결
    * */
    public void add(int index, E e) {

    }

    /*
    * 맨 앞 요소를 제거함
    * 1. 삭제할 요소가 없다면 exception
    * 2. 삭제될 첫 번째 요소의 데이터를 백업
    * 3. 두 번째 노드를 임시 저장
    * 4. 첫 번째 노드의 내부 요소를 모두 삭제
    * 5. head 가 다음 노드를 가리키도록 업데이트
    * 6. 사이즈 감소
    * 7. 만약 유일한 값을 삭제했다면, tail 도 null 처리함
    * 8. 삭제된 데이터를 반환함
    * */
    public E removeFirst() {
        return null;
    }

    /*
    * 맨 앞 요소를 제거함
    * */
    public E remove() {
        return null;
    }

    /*
    * index 번째 요소를 제거함
    * 1. 범위 체크, 불가능하면 exception
    * 2. index 가 0 이면 removeFirst() 로 반환
    * 3. 삭제할 위치의 이전 노드 임시 저장
    * 4. 삭제할 위치의 노드 임시 저장
    * 5. 삭제할 위치의 다음 노드 임시 저장
    * 6. 삭제할 요소의 데이터를 백업
    * 7. 삭제할 노드의 내부 요소를 모두 삭제
    * 8. 사이즈 감소
    * 9. 이전 노드가 다음 노드를 가리키도록 업데이트
    * 10. 삭제된 데이터를 반환
    * */
    public E remove(int index) {
        return null;
    }

    /*
    * 특정 값을 데이터로 갖는 노드를 삭제
    * 1. 삭제할 요소가 없다면 RuntimeException 발생
    * 2. 이전 노드, 삭제 노드, 다음 노드를 저장할 변수 선언
    * 3. 루프 변수 선언 (head 부터)
    * 4. 노드의 next 를 순회하며 해당 값을 찾음
    * 5. 찾은 요소가 없다면 false 로 반환
    * 6. 삭제하려는 노드가 head 라면, removeFirst() 호출 후 true 로 반환
    * 7. 다음 노드에 삭제할 노드 next 요소를 대입함
    * 8. 삭제할 요소 데이터 모두 제거함
    * 9. 사이즈 감소
    * 19. 이전 노드가 다음 노드를 참조하도록 업데이트
    * */
    public boolean remove(Object o) {
        return true;
    }

    /*
    * 맨 마지막 노드를 삭제
    * remove(int index)로 마지막 노드를 지우도록 호출
    * tail 이 있지만, 그 이전 노드를 참조할 prev 가 없기 때문에 처음부터 순회해야함
    * */
    public E removeLast() {
        return null;
    }

    /*
    * index 번째 노드의 데이터를 가져옴
    * 1. 범위 체크
    * 2. search() 로 검색해 데이터를 반환
    * */
    public E get(int index) {
        return null;
    }

    /*
    * index 번째 노드에 데이터를 덮어씀
    * 1. 범위 체크
    * 2. search()로 교체할 노드를 얻음
    * 3. 교체할 노드의 요소를 변경함
    * */
    public void set(int index, E e) {

    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }
        Object[] array = new Object[size];
        int index = 0;
        Node<E> n = head;
        while (n != null) {
            array[index] = (E) n.item;
            index++;
            n = n.next;
        }
        return Arrays.toString(array);
    }
}
