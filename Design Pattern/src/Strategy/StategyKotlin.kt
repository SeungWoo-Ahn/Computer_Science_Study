package Strategy

interface KPaymentStrategy {
    fun pay(amount: Int)
}

class KKAKAOCardStrategy(
    private val name: String,
    private val cardNumber: String,
    private val ccv: String,
    private val dateOfExpiry: String
) : KPaymentStrategy {
    override fun pay(amount: Int) {
        println("$amount paid using KaKaoCard")
    }
}

class KLUNACardStrategy(
    private val email: String,
    private val password: String
) : KPaymentStrategy {
    override fun pay(amount: Int) {
        println("$amount paid using LunaCard")
    }
}

data class KItem(
    val name: String,
    val price: Int
)

class KShoppingCart {
    private val items: MutableList<KItem> = mutableListOf()

    fun addItem(item: KItem) {
        items.add(item)
    }

    fun removeItem(item: KItem) {
        items.remove(item)
    }

    fun calculateTotal(): Int {
        return items.sumOf { it.price }
    }

    fun pay(paymentMethod: KPaymentStrategy) {
        val amount = calculateTotal()
        paymentMethod.pay(amount)
    }
}

fun main() {
    val cart = KShoppingCart()

    val itemA = KItem("a", 100)
    val itemB = KItem("b", 300)
    cart.addItem(itemA)
    cart.addItem(itemB)
    cart.pay(KLUNACardStrategy("aaa@gmail.com", "1234"))
    cart.pay(KKAKAOCardStrategy("Seung Woo", "123456789", "123", "5/20"))
}