package PriorityQueue;

import Queue.Queue;

import java.util.Comparator;

public class PriorityQueue<E> implements Queue<E> {
    private final Comparator<? super E> comparator;
    private static final int DEFAULT_CAPACITY = 10;

    private int size;
    private Object[] array;

    public PriorityQueue() {

    }

    public PriorityQueue(Comparator<? super E> comparator) {

    }

    public PriorityQueue(int capacity) {

    }

    public PriorityQueue(int capacity, Comparator<? super E> comparator) {

    }

    private int getParent(int index) {
        return 0;
    }

    private int getLeftChild(int index) {
        return 0;
    }

    private int getRightChild(int index) {
        return 0;
    }

    private void resize(int newCapacity) {

    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    private void shiftUp(int index, E target) {

    }

    private void shiftUpComparator(int index, E target, Comparator<? super E> comp) {

    }

    private void shiftUpComparable(int index, E target) {

    }

    /**
     * 1. root 가 null 이면, null 반환
     * 2. 아니면 remove() 반환
     */
    @Override
    public E poll() {
        return null;
    }

    /**
     * 1. root 가 null 이면, 예외 던짐
     * 2. 삭제될 데이터 임시 저장
     * 3. 타겟 요소 사이즈에 따라 저장
     * 4. 타겟 노드 비움
     * 5. 사이즈 감소
     * 6. shiftDown
     */
    public E remove() {
        return null;
    }

    private void shiftDown(int index, E target) {

    }

    private void shiftDownComparator(int index, E target, Comparator<? super E> comp) {

    }

    private void shiftDownComparable(int index, E target) {

    }

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

    public boolean contains(Object o) {
        return false;
    }

    public void clear() {

    }
}
