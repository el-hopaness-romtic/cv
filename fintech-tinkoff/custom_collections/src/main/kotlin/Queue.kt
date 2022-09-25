fun <T> queueOf(vararg elements: T): Queue<T> =
    Queue<T>().apply { elements.forEach { this.enqueue(it) } }

class Queue<T> {
    private val array = mutableListOf<T>()

    fun enqueue(element: T) {
        array.add(element)
    }

    fun dequeue(): T {
        if (array.size == 0) throw NoSuchElementException("Queue is empty")
        return array.removeFirst()
    }
}