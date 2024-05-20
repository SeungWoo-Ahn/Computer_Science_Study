package Strategy

sealed class MessageGenerateStrategy {
    abstract fun process(msg: String): String

    data class AttachHeader(val header: String) : MessageGenerateStrategy() {
        override fun process(msg: String): String {
            return "$header $msg"
        }
    }

    data object UpperCase : MessageGenerateStrategy() {
        override fun process(msg: String): String {
            return msg.uppercase()
        }
    }

    data class Etc(val strategy: (String) -> String) : MessageGenerateStrategy() {
        override fun process(msg: String): String {
            return strategy(msg)
        }
    }
}

class MessageConverter(private var messageGenerateStrategy: MessageGenerateStrategy) {
    fun convert(msg: String): String {
        return messageGenerateStrategy.process(msg)
    }

    fun setStrategy(messageGenerateStrategy: MessageGenerateStrategy) {
        this.messageGenerateStrategy = messageGenerateStrategy
    }
}

fun main() {
    val message = "Hi"
    val fromIOSStrategy = MessageGenerateStrategy.Etc { msg -> "$msg : Witten from IOS" }
    val converter = MessageConverter(fromIOSStrategy)
    println(converter.convert(message))

    val headerStrategy = MessageGenerateStrategy.AttachHeader("Message: ")
    converter.setStrategy(headerStrategy)
    println(converter.convert(message))

    converter.setStrategy(MessageGenerateStrategy.UpperCase)
    println(converter.convert(message))
}