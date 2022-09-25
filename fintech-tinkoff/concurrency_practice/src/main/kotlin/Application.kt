fun main() {
    println("Thread name = ${Thread.currentThread().name}\n")

    println("Task 1")
    createThread()
    println()
    createThreadWithRunnable()
    println()
    createThreadWithDsl()
    println()
    createDaemonThread()
    println()
    createThreadsWithDifferentPriorities()
    println()


    println("Task 2")
    Task2().execute()
    println()

    println("Task 3")
    Task3().execute()
}
