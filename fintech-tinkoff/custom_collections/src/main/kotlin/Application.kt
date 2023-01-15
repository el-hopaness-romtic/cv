fun main() {
    val queue = queueOf("first", "second", "third")
    queue.enqueue("fourth")
    println(queue.dequeue())
    println(queue.dequeue())
    println(queue.dequeue())
    println(queue.dequeue())
    try {
        println(queue.dequeue())
    } catch (e: NoSuchElementException) {
        println(e.message)
    }

    println("\n\n\n")

    val stack = stackOf("*******", ".*****.", "..***..")
    stack.push("...*...")
    println(stack.pop())
    println(stack.pop())
    println(stack.pop())
    println(stack.pop())
    try {
        println(stack.pop())
    } catch (e: NoSuchElementException) {
        println(e.message)
    }
}