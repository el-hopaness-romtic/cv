import kotlin.concurrent.thread

class Task2 {
    @Volatile
    var keepRunning = true

    fun execute() {
        println("Volatile property")
        println("${Thread.currentThread().name} main thread")

        thread {
            Thread.sleep(1000)
            keepRunning = false
            println("Set keepRunning to false")
        }

        repeat(3) {
            thread {
                var count = 0
                while (keepRunning) {
                    println("${Thread.currentThread().name} increment count = ${++count}")
                    Thread.sleep(50)
                }
            }
        }

        Thread.sleep(2000)
    }
}
