package Factory

// 팩토리 메서드 패턴
// 상위 클래스에 객체를 생성할 필요한 인터페이스를 만든다.
// 어떤 클래스의 인스턴스를 만들지는 하위 클래스에 맡기는 것.

abstract class PizzaStore {
    fun orderPizza(type: PizzaType): Pizza {
        val pizza = createPizza(type)
        pizza.bake()
        pizza.cut()
        return pizza
    }
    // 객체를 생성할 때 필요한 인터페이스
    abstract fun createPizza(type: PizzaType): Pizza
}

class KoreanPizzaStore : PizzaStore() {
    // 어떤 클래스의 인스턴스를 만들지는 선택
    // 한국 스타일 피자를 제조하고 있음
    override fun createPizza(type: PizzaType): Pizza {
        return when(type) {
            PizzaType.CHICAGO -> KoreanStyleChicagoPizza()
            PizzaType.CHEESE -> KoreanStyleCheesePizza()
        }
    }
}

class NYPizzaStore : PizzaStore() {
    override fun createPizza(type: PizzaType): Pizza {
        return when(type) {
            PizzaType.CHICAGO -> NYStyleChicagoPizza()
            PizzaType.CHEESE -> NYStyleCheesePizza()
        }
    }
}

class KoreanStyleChicagoPizza : ChicagoPizza()

class KoreanStyleCheesePizza : CheesePizza()

class NYStyleChicagoPizza : ChicagoPizza()

class NYStyleCheesePizza : CheesePizza()