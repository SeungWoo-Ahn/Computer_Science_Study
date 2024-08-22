package LinkedList;

import java.util.NoSuchElementException;
import java.util.Objects;

public class MDoublyLinkedList<E> {
    private Node<E> head;
    private Node<E> tail;

    private int size;

    /*
    * 생성자
    * */
    public MDoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    private static class Node<E> {
        private E item;
        private Node<E> prev;
        private Node<E> next;

        Node(E item, Node<E> prev, Node<E> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    /*
    * index 번째 노드를 반환
    * 1. index 가 시작에 가까우면 순차 탐색
    * 2. index 가 끝에 가까우면 역순 탐색
    * */
    private Node<E> search(int index) {
        Node<E> n;
        if (index < size / 2) {
            n = head;
            for (int i = 0; i < index; i++) {
                n = n.next;
            }
        } else {
            n = tail;
            for (int i = size - 1; i > index; i--) {
                n = n.prev;
            }
        }
        return n;
    }

    /*
    * 1. head 를 임시 저장함
    * 2. 새 노드를 추가함
    * 3. 사이즈 증가
    * 4. head 를 새 노드로 업데이트
    * 5. 최초로 추가된 경우, tail 도 업데이트
    * 6. 아니라면, 기존 첫 노드가 새 노드를 참조하도록 업데이트
    * */
    public void addFirst(E e) {
        Node<E> originFirst = head;
        Node<E> first = new Node<>(e, null, originFirst);
        size++;
        head = first;
        if (originFirst == null) {
            tail = first;
        } else {
            originFirst.prev = first;
        }
    }

    /*
    * 1. tail 을 임시 저장함
    * 2. 새 노드를 추가함
    * 3. 사이즈 증가
    * 4. tail 를 새 노드로 업데이트
    * 5. 최초로 추가된 경우, head 도 업데이트
    * 6. 아니라면, 기존 마지막 노드가 새 노드를 참조하도록 업데이트
    * */
    public void addLast(E e) {
        Node<E> originLast = tail;
        Node<E> last = new Node<>(e, originLast, null);
        size++;
        tail = last;
        if (originLast == null) {
            head = last;
        } else {
            originLast.next = last;
        }
    }

    /*
    * 마지막에 요소를 추가함
    * */
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    /*
    * 1. index 범위 체크
    * 2. index 가 0이라면 addFirst() 호출 후 반환
    * 3. index 가 마지막 위치라면 addLast() 호출 후 반환
    * 4. 추가하려는 위치의 다음 노드 임시 저장
    * 5. 추가하려는 위치의 이전 노드 임시 저장
    * 6. 새 노드 생성
    * 7. 사이즈 증가
    * 8. 이전 노드의 next 를 새 노드에 연결
    * 9. 다음 노드의 prev 를 새 노드에 연결
    * */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            addFirst(e);
            return;
        }
        if (index == size) {
            addLast(e);
            return;
        }
        Node<E> nxtNode = search(index);
        Node<E> prevNode = nxtNode.prev;
        Node<E> newNode = new Node<>(e, prevNode, nxtNode);
        size++;
        prevNode.next = newNode;
        nxtNode.prev = newNode;
    }

    /*
    * 1. 삭제할 요소가 없다면, NoSuchElementException
    * 2. 삭제될 첫 번째 노드의 데이터를 임시 저장
    * 3. 두 번째 노드를 임시 저장
    * 4. 첫 번째 노드의 요소를 모두 삭제
    * 5. 사이즈 감소
    * 6. head 가 다음 노드를 가리키도록 업데이트
    * 7. 삭제해서 빈 리스트가 되었다면, tail 도 null 처리
    * 8. 아니라면 두 번째였던 노드의 prev 를 null 처리
    * 9. 삭제된 노드의 데이터를 반환
    * */
    public E removeFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        E data = head.item;
        Node<E> first = head.next;
        head.item = null;
        head.next = null;
        size--;
        head = first;
        if (first == null) {
            tail = null;
        } else {
            first.prev = null;
        }
        return data;
    }

    /*
    * 첫 번째 노드를 제거하며 해당 데이터를 반환
    * */
    public E remove() {
        return removeFirst();
    }

    /*
    * 1. 삭제할 요소가 없다면, NoSuchElementException
    * 2. 삭제될 마지막 요소의 데이터를 임시 저장
    * 3. 그 이전 노드를 임시 저장
    * 4. 삭제할 노드의 내부 요소를 모두 삭제
    * 5. 사이즈 감소
    * 6. tail 업데이트
    * 7. 삭제 후 빈 리스트가 되면, head 도 null 처리
    * 8. 아니라면, 이전 노드의 next 를 null 처리
    * 9. 삭제된 노드의 데이터를 반환
    * */
    public E removeLast() {
        if (tail == null) {
            throw new NoSuchElementException();
        }
        E data = tail.item;
        Node<E> last = tail.prev;
        tail.item = null;
        tail.prev = null;
        size--;
        tail = last;
        if (last == null) {
            head = null;
        } else {
            last.next = null;
        }
        return data;
    }

    /*
    * 1. 범위 체크
    * 2. index 가 0이면 removeFirst() 반환
    * 3. index 가 마지막 위치라면 removeLast() 반환
    * 4. 삭제할 노드를 임시 저장
    * 5. 삭제할 노드의 이전, 다음 노드를 임시 저장
    * 6. 삭제할 노드의 데이터를 임시 저장
    * 7. 삭제할 노드의 내부 요소를 모두 삭제
    * 8. 사이즈 감소
    * 9. 이전 노드와 다음 노드의 prev, next 를 변경
    * 10. 삭제한 노드의 데이터를 반환
    * */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return removeFirst();
        }
        if (index == size - 1) {
            return removeLast();
        }
        Node<E> delNode = search(index);
        Node<E> prevNode = delNode.prev;
        Node<E> nxtNode = delNode.next;
        E data = delNode.item;
        delNode.item = null;
        delNode.prev = null;
        delNode.next = null;
        size--;
        prevNode.next = nxtNode;
        nxtNode.prev = prevNode;
        return data;
    }

    /*
    * 1. 삭제 노드를 저장할 변수 선언
    * 2. 리스트 순회할 변수 선언
    * 3. 리스트 순회하며 삭제할 노드를 찾음
    * 4. 찾은 요소가 없다면 false 반환
    * 5. 찾은 요소가 head 이라면, removeFirst() 호출 후 반환
    * 6. 찾은 요소가 tail 이라면, removeLast() 호출 후 반환
    * 7. 이전 노드, 다음 노드를 임시 저장
    * 8. 삭제할 노드의 내부 요소를 모두 삭제
    * 9. 사이즈 감소
    * 10. 이전 노드와 다음 노드끼리 연결
    * */
    public boolean remove(Object o) {
        Node<E> n = null;
        Node<E> i = head;
        while (i != null) {
            if (Objects.equals(o, i.item)) {
                n = i;
                break;
            }
            i = i.next;
        }
        if (n == null) {
            return false;
        }
        if (n == head) {
            removeFirst();
            return true;
        }
        if (n == tail) {
            removeLast();
            return true;
        }
        Node<E> prevNode = n.prev;
        Node<E> nxtNode = n.next;
        n.item = null;
        n.prev = null;
        n.next = null;
        size--;
        prevNode.next = nxtNode;
        nxtNode.prev = prevNode;
        return true;
    }

    /*
    * 1. 범위 체크
    * 2. search() 로 검색 후 해당 데이터를 반환
    * */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return search(index).item;
    }

    /*
    * 1. 범위 체크
    * 2. search() 로 교체할 노드 임시 저장
    * 3. 데이터 교체
    * */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> n = search(index);
        n.item = e;
    }

    /*
    * 순서대로 검색해서 일치하는 첫 요소의 위치를 반환
    * */
    public int indexOf(Object o) {
        Node<E> n = head;
        int i = 0;
        while (n != null) {
            if (Objects.equals(o, n.item)) {
                return i;
            }
            n = n.next;
            i++;
        }
        return -1;
    }

    /*
    * 거꾸로 검색해서 일치하는 첫 요소의 위치를 반환
    * */
    public int lastIndexOf(Object o) {
        Node<E> n = tail;
        int i = size - 1;
        while (n != null) {
            if (Objects.equals(o, n.item)) {
                return i;
            }
            n = n.prev;
            i--;
        }
        return -1;
    }
}
