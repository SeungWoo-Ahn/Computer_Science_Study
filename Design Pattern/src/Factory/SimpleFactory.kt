package Factory

enum class PizzaType {
    CHICAGO,
    CHEESE
}

interface Pizza {
    fun bake()
    fun cut()
}

open class ChicagoPizza : Pizza {
    override fun bake() {
        // 굽기
    }

    override fun cut() {
        // 자르기
    }
}

open class CheesePizza : Pizza {
    override fun bake() {
        // 굽기
    }

    override fun cut() {
        // 자르기
    }
}

class SimplePizzaFactory {
    fun createPizza(type: PizzaType): Pizza {
        return when (type) {
            PizzaType.CHICAGO -> ChicagoPizza()
            PizzaType.CHEESE -> CheesePizza()
        }
    }
}