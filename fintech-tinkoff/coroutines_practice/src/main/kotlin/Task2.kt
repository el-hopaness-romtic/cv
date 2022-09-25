import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce


@ExperimentalCoroutinesApi
class Task2 {

    private suspend fun CoroutineScope.notificator(seconds: Int) = produce {
        println("Sending notification every $seconds second(s)")
        val timeMillis = seconds * 1000L
        repeat(5) {
            delay(timeMillis)
            send("Notification №${it + 1}")
        }
    }


    fun execute() = runBlocking {
        val receiver: ReceiveChannel<String> = notificator(2)

        while (!receiver.isClosedForReceive) {
            println(receiver.receive())
        }
    }
}
