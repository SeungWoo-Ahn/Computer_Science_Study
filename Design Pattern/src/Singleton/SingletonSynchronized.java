package Singleton;

class SingletonSynchronized {
    private static SingletonSynchronized instance;

    private SingletonSynchronized() {}

    // Java 는 멀티스레드에서 동작
    // synchronized 키워드로 인스턴스를 반환하기 전까지 격리 공간에 둠
    // 최초로 접근한 스레드가 해당 메서드 호출시에 다른 스레드가 접근하지 못하도록 lock 을 건다.
    // 하지만 getInstance 를 호출할 때마다 lock 이 걸려 성능저하 발생
    public static synchronized SingletonSynchronized getInstance() {
        if (instance == null) {
            instance = new SingletonSynchronized();
        }
        return instance;
    }
}
