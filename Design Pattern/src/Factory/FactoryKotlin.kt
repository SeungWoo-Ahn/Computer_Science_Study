package Factory

enum class KCoffeeType {
    LATTE,
    ESPRESSO
}

abstract class KCoffee {
    abstract val name: String
}

class KLatte : KCoffee() {
    override val name: String = "latte"
}

class KEspresso : KCoffee() {
    override val name: String = "espresso"
}

object KCoffeeFactory {
    fun createCoffee(type: KCoffeeType): KCoffee {
        return when (type) {
            KCoffeeType.LATTE -> KLatte()
            KCoffeeType.ESPRESSO -> KEspresso()
        }
    }
}

fun main() {
    val coffee = KCoffeeFactory.createCoffee(KCoffeeType.LATTE)
    println(coffee.name)
}