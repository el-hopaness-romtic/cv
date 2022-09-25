import data.*
import kotlinx.coroutines.*
import java.time.LocalDate


class Task1 {

    private val mainUserInfos: List<MainUserInfo> = listOf(
        MainUserInfo(1, "Star Flint"),
        MainUserInfo(2, "Kelli Teresa"),
        MainUserInfo(3, "Theresa Micky"),
        MainUserInfo(4, "Steinn Jason"),
        MainUserInfo(5, "Anna Heribert"),
        MainUserInfo(6, "Terry Odelia"),
    )

    private val userinfos: List<OptionalUserInfo?> = listOf(
        OptionalUserInfo(1, LocalDate.of(2000, 10, 6), "Orange"),
        OptionalUserInfo(2, LocalDate.of(1997, 7, 19), null),
        OptionalUserInfo(3, LocalDate.of(1996, 11, 18), "Red"),
        null,
        OptionalUserInfo(5, LocalDate.of(1986, 1, 5), "Pink"),
        OptionalUserInfo(6, null, "Green"),
    )

    private suspend fun getMainUserInfo(id: Int): MainUserInfo {
        delay(1000)
        return mainUserInfos[id]
    }

    private suspend fun getOptionalUserInfo(id: Int): OptionalUserInfo? {
        delay(1000)
        return userinfos[id]
    }

    fun execute() = runBlocking(Dispatchers.IO) {
        println("Making network requests")
        for (i in mainUserInfos.indices) {
            val a = async { getMainUserInfo(i) }
            val b = async { getOptionalUserInfo(i) }

            val result = AllUserInfo(a.await(), b.await())
            println(result)
        }
    }
}
