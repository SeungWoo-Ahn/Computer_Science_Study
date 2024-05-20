package Strategy

data class Book(val title: String, val price: Double)

enum class MembershipType {
    REGULAR, PREMIUM
}

data class Customer(val name: String, val membershipType: MembershipType)

sealed class DiscountStrategy {
    abstract fun calculateDiscount(book: Book): Double

    data object RegularCustomerDiscountStrategy : DiscountStrategy() {
        override fun calculateDiscount(book: Book): Double {
            return book.price * 0.1
        }
    }

    data object PremiumCustomerDiscountStrategy : DiscountStrategy() {
        override fun calculateDiscount(book: Book): Double {
            return book.price * 0.2
        }
    }
}

class DiscountCalculator(private val discountStrategy: DiscountStrategy) {
    fun calculateDiscount(book: Book): Double {
        return discountStrategy.calculateDiscount(book)
    }
}

fun main() {
    fun createDiscountCalculator(customer: Customer): DiscountCalculator {
        val discountStrategy = when (customer.membershipType) {
            MembershipType.REGULAR -> DiscountStrategy.RegularCustomerDiscountStrategy
            MembershipType.PREMIUM -> DiscountStrategy.PremiumCustomerDiscountStrategy
        }
        return DiscountCalculator(discountStrategy)
    }

    val book = Book("Effective Java", 100.0)

    val regularCustomer = Customer("regular", MembershipType.REGULAR)
    val calculatorForRegular = createDiscountCalculator(regularCustomer)
    println(calculatorForRegular.calculateDiscount(book))

    val premiumCustomer = Customer("premium", MembershipType.PREMIUM)
    val calculatorForPremium = createDiscountCalculator(premiumCustomer)
    println(calculatorForPremium.calculateDiscount(book))
}