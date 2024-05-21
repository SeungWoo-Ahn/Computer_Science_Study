package Observer

interface NewsObserver {
    fun notify(team: Team, message: String)
}

interface NewsSubscriber {
    fun subscribe(observer: NewsObserver)
    fun unsubscribe(observer: NewsObserver)
    fun notify(message: String)
}

enum class Team {
    TOTTENHAM,
    LIVERPOOL
}

data class Fan(private val name: String) : NewsObserver {
    override fun notify(team: Team, message: String) {
        println("${name}님, ${team}의 새로운 소식: $message")
    }
}

class NewsPublisher(private val team: Team) : NewsSubscriber {
    private val observers: MutableList<NewsObserver> = mutableListOf()

    override fun subscribe(observer: NewsObserver) {
        if (!observers.contains(observer))
            observers.add(observer)
    }

    override fun unsubscribe(observer: NewsObserver) {
        observers.remove(observer)
    }

    override fun notify(message: String) {
        observers.forEach { observer ->
            observer.notify(team, message)
        }
    }
}

class NewsSystemBuilder(team: Team) {
    private val newPublisher = NewsPublisher(team)

    fun subscribe(fan: Fan) = apply { newPublisher.subscribe(fan) }

    fun unsubscribe(fan: Fan) = apply { newPublisher.unsubscribe(fan) }

    fun notify(message: String) = apply { newPublisher.notify(message) }

    companion object {
        fun forTeam(team: Team) = NewsSystemBuilder(team)
    }
}

fun main() {
    val fanA = Fan("A")
    val fanB = Fan("B")

    NewsSystemBuilder.forTeam(Team.TOTTENHAM)
        .subscribe(fanA)
        .subscribe(fanB)
        .notify("손흥민 슛~~")
        .unsubscribe(fanA)
        .notify("골~~")

    NewsSystemBuilder.forTeam(Team.LIVERPOOL)
        .subscribe(fanA)
        .notify("리버풀 챔스 진출")
}