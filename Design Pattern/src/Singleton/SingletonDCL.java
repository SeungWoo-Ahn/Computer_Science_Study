package Singleton;

class SingletonDCL {
    // Java 는 스레드 2개가 열리면 변수를 메인 메모리(RAM)이 아닌 캐시메모리에서 각각의 캐시메모리를 기반으로 가져오게 된다.
    // 멀티스레드 환경에서 각자의 캐시메모리에서 instance 를 확인하기 때문에 두 개 이상의 instance 가 생성될 수 있는 문제가 있다.
    // volatile 키워드로 메인 메모리를 기반으로 변수를 저장하고 읽어오기 때문에 문제를 해결할 수 있다.
    private volatile SingletonDCL instance;

    private SingletonDCL() {}

    // 이중 확인 잠금 (Double Checked Locking)
    // 인스턴스 생성 여부를 싱글톤 패턴 잠금 전에 한 번, 객체를 생성하기 전에 한 번 총 2번 체크한다.
    // 인스턴스가 존재하지 않을 때만 잠금을 걸 수 있게 때문에 SingletonSynchronized 의 멀티스레드 환경에서 문제를 해결할 수 있다.
    public SingletonDCL getInstance() {
        if (instance == null) {
            synchronized (SingletonDCL.class) {
                if (instance == null) {
                    instance = new SingletonDCL();
                }
            }
        }
        return instance;
    }
}
