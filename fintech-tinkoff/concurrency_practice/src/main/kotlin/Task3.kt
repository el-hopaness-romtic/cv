import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

class Task3 {
    fun execute() {
        val results = mutableListOf<Pair<Int, Long>>()

        arrayOf(10, 20, 30).forEach { poolSize ->
            val counter = AtomicInteger(0)
            val executorService = Executors.newFixedThreadPool(poolSize)

            val start = System.nanoTime()
            repeat(poolSize) {
                executorService.submit {
                    while (counter.get() < 1_000_000) {
                        counter.incrementAndGet()
                    }
                }
            }
            executorService.shutdown()
            executorService.awaitTermination(5, TimeUnit.SECONDS)

            results.add(poolSize to System.nanoTime() - start)
        }

        results.apply {
            sortBy { it.second }
            forEach {
                println("Pool with ${it.first} threads - ${it.second} ns")
            }
        }
    }
}
