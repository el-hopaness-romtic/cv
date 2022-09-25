import kotlin.concurrent.thread

fun createThread() {
    Thread {
        println("Simple thread, name = ${Thread.currentThread().name}")
    }.apply {
        start()
        join()
    }
}

fun createThreadWithRunnable() {
    val runnable = Runnable {
        println("Thread created with runnable, name = ${Thread.currentThread().name}")
    }
    Thread(runnable).apply {
        start()
        join()
    }
}

fun createThreadWithDsl() {
    thread {
        println("Thread created with DSL, name = ${Thread.currentThread().name}")
    }.join()
}

fun createDaemonThread() {
    thread(isDaemon = true) {
        println(
            "Daemon thread, name = ${Thread.currentThread().name}, " +
                    "isDaemon = ${Thread.currentThread().isDaemon}"
        )
    }.join()
}

fun createThreadsWithDifferentPriorities() {
    println("Normal priority is ${Thread.NORM_PRIORITY}")
    println("Maximum priority is ${Thread.MAX_PRIORITY}")
    println("Minimum priority is ${Thread.MIN_PRIORITY}")

    val lambda: () -> Unit = {
        println(
            "Thread with modified priority, name = ${Thread.currentThread().name}, " +
                    "priority = ${Thread.currentThread().priority}"
        )
    }

    val threads: List<Thread> = listOf(
        thread(priority = 6, block = lambda),
        thread(priority = 2, block = lambda),
        thread(priority = 9, block = lambda),
        thread(priority = 1, block = lambda),
    )

    threads.forEach { it.join() }
}
