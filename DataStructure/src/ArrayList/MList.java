package ArrayList;

public interface MList<E> {
    boolean add(E e);
    void add(int index, E e);

    boolean remove(Object o);
    E remove(int index);

    E get(int index);
    void set(int index, E e);

    boolean contains(Object o);
    int indexOf(Object o);
    int lastIndexOf(Object o);

    int size();
    boolean isEmpty();

    void clear();
}
