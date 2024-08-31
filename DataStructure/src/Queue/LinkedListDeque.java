package Queue;

import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedListDeque<E> implements Queue<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedListDeque() {
        this.head = null;
        this.tail = null;
        this.size = 0;
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
        return offerLast(e);
    }

    public boolean offerFirst(E e) {
        Node<E> newNode = new Node<>(e);
        if (head != null) {
            head.prev = newNode;
        }
        newNode.next = head;
        head = newNode;
        size++;
        if (head.next == null) {
            tail = head;
        }
        return true;
    }

    public boolean offerLast(E e) {
        if (size == 0) {
            return offerFirst(e);
        }
        Node<E> newNode = new Node<>(e);
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        size++;
        return true;
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    public E pollFirst() {
        if (size == 0) {
            return null;
        }
        E data = head.data;
        Node<E> second = head.next;
        head.data = null;
        head.next = null;
        if (second != null) {
            second.prev = null;
        }
        head = null;
        head = second;
        size--;
        if (size == 0) {
            tail = null;
        }
        return data;
    }

    public E pollLast() {
        if (size == 0) {
            return null;
        }
        E data = tail.data;
        Node<E> beforeLast = tail.prev;
        tail.data = null;
        tail.prev = null;
        if (beforeLast != null) {
            beforeLast.next = null;
        }
        tail = null;
        tail = beforeLast;
        size--;
        if (size == 0) {
            head = null;
        }
        return data;
    }

    public E remove() {
        return removeFirst();
    }

    public E removeFirst() {
        E data = pollFirst();
        if (data == null) {
            throw new NoSuchElementException();
        }
        return data;
    }

    public E removeLast() {
        E data = pollLast();
        if (data == null) {
            throw new NoSuchElementException();
        }
        return data;
    }

    @Override
    public E peek() {
        return peekFirst();
    }

    public E peekFirst() {
        if (size == 0) {
            return null;
        }
        return head.data;
    }

    public E peekLast() {
        if (size == 0) {
            return null;
        }
        return tail.data;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        Node<E> node = head;
        while (node != null) {
            if (Objects.equals(node.data, o)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public void clear() {
        Node<E> node = head;
        while (node != null) {
            Node<E> nextNode = node.next;
            node.data = null;
            node.prev = null;
            node.next = null;
            node = nextNode;
        }
        head = tail = null;
        size = 0;
    }
}
