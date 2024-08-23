package Stack;

public interface StackInterface<E> {
    E push(E e);
    E pop();
    E peek();
    int search(Object o);
    int size();
    void clear();
    boolean empty();
}
