package Factory

// 추상 팩토리 패턴
// 구현 클래스에 의존하지 않고, 서로 연관되거나 의존적인 객체로 이루어진
// 제품군(Family)을 생산하는 인터페이스를 제공하는 패턴

/*************** 제품군 *******************/
sealed interface Dough {
    data object ThinDough : Dough
    data object ThickDough : Dough
}

sealed interface Source {
    data object RedSource : Source
    data object WhiteSource : Source
}

interface Cheese {
    data object Cheddar : Cheese
    data object Mozzarella : Cheese
}

/*********** 제품군 생산 인터페이스**********/
interface PizzaIngredientFactory {
    fun createDough(): Dough
    fun createSource(): Source
    fun createCheese(): Cheese
}

class NYPizzaIngredientFactory : PizzaIngredientFactory {
    override fun createDough(): Dough {
        return Dough.ThinDough
    }

    override fun createSource(): Source {
        return Source.RedSource
    }

    override fun createCheese(): Cheese {
        return Cheese.Cheddar
    }
}

abstract class MyPizza {
    lateinit var dough: Dough
    lateinit var source: Source
    lateinit var cheese: Cheese

    abstract fun prepare()
    abstract fun bake()
    override fun toString(): String {
        return "도우: $dough, 소스: $source, 치즈: $cheese"
    }
}

// 어떤 공장에서 오는 재료인지 몰라도 됨
class MyChicagoPizza(private val factory: PizzaIngredientFactory) : MyPizza() {
    override fun prepare() {
        dough = factory.createDough()
        source = factory.createSource()
        cheese = factory.createCheese()
    }

    override fun bake() {
        // 굽기
    }
}

class MyCheesePizza(private val factory: PizzaIngredientFactory) : MyPizza() {
    override fun prepare() {
        dough = factory.createDough()
        source = factory.createSource()
        cheese = factory.createCheese()
    }

    override fun bake() {
        //굽기
    }
}

abstract class MyPizzaStore {
    fun orderPizza(type: PizzaType): MyPizza {
        val pizza = createPizza(type)
        pizza.prepare()
        pizza.bake()
        return pizza
    }

    abstract fun createPizza(type: PizzaType): MyPizza
}

class MyNYPizzaStore : MyPizzaStore() {
    override fun createPizza(type: PizzaType): MyPizza {
        val factory = NYPizzaIngredientFactory()
        return when (type) {
            PizzaType.CHICAGO -> MyChicagoPizza(factory)
            PizzaType.CHEESE -> MyCheesePizza(factory)
        }
    }
}

fun main() {
    val store = MyNYPizzaStore()
    val pizza = store.orderPizza(PizzaType.CHICAGO)
    println(pizza)
}