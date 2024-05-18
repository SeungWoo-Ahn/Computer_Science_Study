package Factory;

enum CoffeeType {
    LATTE,
    ESPRESSO
}

// 상위 클래스: 중요한 뼈대를 결정
// 인스턴스 생성 방식을 알 필요가 없음
abstract class Coffee {
    protected String name;

    public String getName() {
        return name;
    }
}

// 하위 클래스: 객체 생성에 대한 구체적인 내용을 결정
class Latte extends Coffee {
    public Latte() {
        name = "latte";
    }
}

class Espresso extends Coffee {
    public Espresso() {
        name = "espresso";
    }
}

class CoffeeFactory {
    public static Coffee createCoffee(CoffeeType type) {
        return switch (type) {
            case LATTE -> new Latte();
            case ESPRESSO -> new Espresso();
        };
    }
}

class Main {
    public static void main(String[] args) {
        Coffee coffee = CoffeeFactory.createCoffee(CoffeeType.LATTE);
        System.out.println(coffee.getName());
    }
}