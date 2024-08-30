package Queue;

import java.util.NoSuchElementException;

public class LinkedListQueue<E> implements Queue<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedListQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private static class Node<E> {
        private E data;
        private Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    @Override
    public boolean offer(E e) {
        Node<E> newNode = new Node<>(e);
        if (size == 0) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
        return true;
    }

    @Override
    public E poll() {
        if (size == 0) {
            return null;
        }
        E data = head.data;
        Node<E> second = head.next;
        head.data = null;
        head.next = null;
        size--;
        head = second;
        return data;
    }

    public E remove() {
        E data = poll();
        if (data == null) {
            throw new NoSuchElementException();
        }
        return data;
    }

    @Override
    public E peek() {
        if (size == 0) {
            return null;
        }
        return head.data;
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
            if (node.data.equals(o)) {
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
            node.next = null;
            node = nextNode;
        }
        head = tail = null;
        size = 0;
    }
}
