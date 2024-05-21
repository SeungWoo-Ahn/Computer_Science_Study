package Observer

interface KObserver {
    fun update()
}

interface KSubject {
    fun register(obj: KObserver)
    fun unregister(obj: KObserver)
    fun notifyObservers()
    fun getUpdate(obj: KObserver): Any
}

class KTopic(
    private val observers: MutableList<KObserver> = mutableListOf(),
    private var message: String = ""
) : KSubject {
    override fun register(obj: KObserver) {
        if (!observers.contains(obj))
            observers.add(obj)
    }

    override fun unregister(obj: KObserver) {
        observers.remove(obj)
    }

    override fun notifyObservers() {
        observers.forEach(KObserver::update)
    }

    override fun getUpdate(obj: KObserver): Any {
        return message
    }

    fun postMessage(msg: String) {
        println("Message sent to Topic: $msg")
        message = msg
        notifyObservers()
    }
}

class KTopicSubscriber(
    private val name: String,
    private val topic: KSubject
) : KObserver {
    override fun update() {
        val msg = topic.getUpdate(this).toString()
        println("$name :: got message >> $msg")
    }
}

fun main() {
    val topic = KTopic()
    val a = KTopicSubscriber("a", topic)
    val b = KTopicSubscriber("b", topic)
    val c = KTopicSubscriber("c", topic)
    topic.register(a)
    topic.register(b)
    topic.register(c)

    topic.postMessage("This is observer pattern")
}