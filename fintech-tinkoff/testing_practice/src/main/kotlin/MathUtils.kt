class MathUtils {

    fun isPrime(n: Int): Boolean {
        if (n < 2) return false

        var i = 2
        while (i * i <= n) {
            if (n % i == 0) {
                return false
            }
            i++
        }

        return true
    }

    fun fibonacciNumbers(n: Int): IntArray {
        if (n < 1) return intArrayOf()
        if (n == 1) return intArrayOf(0)

        val result = mutableListOf(0, 1)
        var i = 2
        while (i < n) {
            result.add(result[result.size - 2] + result[result.size - 1])
            i++
        }

        return result.toIntArray()
    }
}
