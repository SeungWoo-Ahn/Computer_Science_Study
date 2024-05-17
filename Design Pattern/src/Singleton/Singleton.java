package Singleton;

class Singleton {
    // static + 중첩클래스(홀더) : 애플리케이션 당 단 하나 + singleInstanceHolder 를 실제로 사용할 때까지 미룸
    //                           정적 초기화로 생성되므로 스레드 세이프하다. (많은 쓰레드에서 getInstance 를 호출해도 상관x)
    // final: 다시 값이 할당되지 않게(read only)
    private static class singleInstanceHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
    public static Singleton getInstance() {
        return singleInstanceHolder.INSTANCE;
    }
}

class Main {
    public static void main(String[] args) {
        Singleton a = Singleton.getInstance();
        Singleton b = Singleton.getInstance();
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        if (a == b) {
            System.out.println(true);
        }
    }
}
