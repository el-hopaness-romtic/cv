fun String.isPalindrome(): Boolean {
    if (isEmpty()) throw IllegalArgumentException("String is empty")

    val halfLength = length / 2 - 1
    for (i in 0..halfLength) {
        if (this[i] != this[lastIndex - i]) return false
    }
    return true
}
