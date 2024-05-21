package Observer

interface Subscriber {
    fun handleMessage(message: String)
}

data class User(val name: String) : Subscriber {
    override fun handleMessage(message: String) {
        println(message)
    }
}

interface ChatObserver {
    fun register(subject: String, subscriber: Subscriber)
    fun release(subject: String, subscriber: Subscriber)
    fun notify(user: User, subject: String, message: String)
}

class ChatServer : ChatObserver {
    private val subscribers: MutableMap<String, MutableList<Subscriber>> = mutableMapOf()

    override fun register(subject: String, subscriber: Subscriber) {
        if (!subscribers.containsKey(subject)) {
            subscribers[subject] = mutableListOf()
        }
        subscribers[subject]?.add(subscriber)
    }

    override fun release(subject: String, subscriber: Subscriber) {
        if (subscribers.containsKey(subject))
            subscribers[subject]?.remove(subscriber)
    }

    override fun notify(user: User, subject: String, message: String) {
        if (subscribers.containsKey(subject)) {
            val userMessage = "${user.name}: $message"
            subscribers[subject]?.forEach { subscriber ->
                subscriber.handleMessage(userMessage)
            }
        }
    }
}

fun main() {
    val chatServer = ChatServer()
    val user1 = User("A")
    val user2 = User("B")

    chatServer.register("더 8 게임", user1)
    chatServer.register("더 8 게임", user2)
    chatServer.register("디자인 패턴", user1)

    chatServer.notify(user1, "더 8 게임", "The 8 game")
    chatServer.notify(user2, "디자인 패턴", "Observer")
}