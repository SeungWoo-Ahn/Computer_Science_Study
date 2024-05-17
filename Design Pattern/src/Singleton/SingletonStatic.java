package Singleton;

class SingletonStatic {
    // 정적 멤버로 인스턴스를 만든다.
    // 최초에 JVM 이 클래스 로딩 때 미리 인스턴스를 생성한다.
    // 하지만 불필요한 자원낭비이다. 싱글톤 인스턴스가 필요없는 경우에도 만들기 때문이다.
    private final static SingletonStatic instance = new SingletonStatic();

    private SingletonStatic() {}

    public static SingletonStatic getInstance() {
        return instance;
    }
}
