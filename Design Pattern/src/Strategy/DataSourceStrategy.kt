package Strategy

data class User(val name: String)

interface UserDataSource {
    fun save(user: User)
}

class RemoteUserDateSource : UserDataSource {
    override fun save(user: User) {
        println("Save in remote data source")
    }
}

class LocalUserDataSource : UserDataSource {
    override fun save(user: User) {
        println("Save in local data source")
    }
}

class UserRepository(
    private val userDataSource: UserDataSource
) {
    fun save(user: User) {
        userDataSource.save(user)
    }
}

fun main() {
    val user = User("Seung Woo")

    val localDataSource = LocalUserDataSource()
    val repositoryWithLocalDataSource = UserRepository(localDataSource)
    repositoryWithLocalDataSource.save(user)

    val remoteDataSource = RemoteUserDateSource()
    val repositoryWithRemoteDataSource = UserRepository(remoteDataSource)
    repositoryWithRemoteDataSource.save(user)
}