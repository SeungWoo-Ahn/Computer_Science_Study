package Singleton

// Kotlin 에서 object 클래스는 싱글톤과 같은 성질이다.
// 스레드 세이프하고 실제로 사용할 때 초기화된다. (Thread safe and lazy)
// 하지만 생성자가 허용되지 않는다.
object SingletonKotlin {

}

// Double-Checked Locking 의 Kotlin 버전이다.
// Thread safe and lazy 로 object 같이 동작한다.
class SingletonDCLKotlin private constructor() {
    companion object {
        @Volatile private var instance: SingletonDCLKotlin? = null

        fun getInstance()
            = instance ?: synchronized(this) {
                instance ?: SingletonDCLKotlin().also { instance = it }
            }
    }
}

// 보다 간결하게 싱글톤을 만드는 방법이다.
// Thread safe and lazy 로 object 같이 동작한다.
class SingletonSimple private constructor() {
    companion object {
        val INSTANCE: SingletonSimple by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { SingletonSimple() }
    }
}

fun main() {
    val dcl1 = SingletonDCLKotlin.getInstance()
    val dcl2 = SingletonDCLKotlin.getInstance()
    if (dcl1 == dcl2) {
        println(true)
    }

    val simple1 = SingletonSimple.INSTANCE
    val simple2 = SingletonSimple.INSTANCE
    if (simple1 == simple2) {
        println(true)
    }
}