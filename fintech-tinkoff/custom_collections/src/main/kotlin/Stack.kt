fun <T> stackOf(vararg elements: T): Stack<T> =
    Stack<T>().apply { elements.forEach { this.push(it) } }

class Stack<T> {
    private val array = mutableListOf<T>()

    fun push(element: T) {
        array.add(element)
    }

    fun pop(): T {
        if (array.size == 0) throw NoSuchElementException("Stack is empty")
        return array.removeLast()
    }
}