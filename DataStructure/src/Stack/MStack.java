package Stack;

public class MStack<E> implements StackInterface<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ARRAY = {};

    private Object[] array;
    private int size;

    public MStack() {

    }

    public MStack(int capacity) {

    }

    /*
    * 1. 빈 배열일 경우
    * 2. 가득찰 경우
    * 3. 사이즈가 용량 절반 이하일 경우
    * */
    private void resize() {

    }

    /*
    * 1. 용량이 꽉 찼다면, resize() 호출
    * 2. 마지막 위치에 요소 추가
    * */
    @Override
    public E push(E e) {
        return null;
    }

    /*
    * 1. 삭제할 요소가 없다면, EmptyStackException
    * 2. 삭제할 요소 임시 저장
    * 3. 요소 삭제
    * 4. 사이즈 감소
    * 5. resize()
    * */
    @Override
    public E pop() {
        return null;
    }

    /*
    * 1. 삭제할 요소가 없다면, EmptyStackException
    * 2. 마지막 요소 반환
    * */
    @Override
    public E peek() {
        return null;
    }

    /*
    * 스택의 Top 에서부터 찾아서 일치하는 첫 번째 요소가 Top 에서 몇 번째에 있는지 반환
    * 1. 찾으려는 값이 null 일때
    * 2. 찾으려는 값이 null 이 아닐때
    * */
    @Override
    public int search(Object o) {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    /*
    * 1. 저장된 데이터 모두 null 처리
    * 2. 사이즈 0
    * 3. resize() 로 현재 용적을 절반으로 줄임
    * */
    @Override
    public void clear() {

    }

    @Override
    public boolean empty() {
        return false;
    }
}
