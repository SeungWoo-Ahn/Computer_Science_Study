package Queue;

public class LinkedListDeque<E> implements Queue<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedListDeque() {

    }

    private static class Node<E> {
        private E data;
        private Node<E> prev;
        private Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    public boolean offerFirst(E e) {
        return false;
    }

    public boolean offerLast(E e) {
        return false;
    }

    @Override
    public E poll() {
        return null;
    }

    public E pollFirst() {
        return null;
    }

    public E pollLast() {
        return null;
    }

    public E remove() {
        return null;
    }

    public E removeFirst() {
        return null;
    }

    public E removeLast() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    public E peekFirst() {
        return null;
    }

    public E peekLast() {
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

    public void clear() {

    }
}
