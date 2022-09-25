import org.junit.jupiter.params.provider.ValueSource
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertArrayEquals

class MathUtilsTest {

    @ParameterizedTest(name = "isPrime | if inappropriate argument then false: {0}")
    @ValueSource(ints = [-3, -1, 0, 1])
    fun `if inappropriate argument then false`(n: Int) {
        val result = MathUtils().isPrime(n)

        assertFalse(result)
    }

    @ParameterizedTest(name = "isPrime | if not prime then false: {0}")
    @ValueSource(ints = [4, 6, 8, 9, 10, 117])
    fun `if not prime then false`(n: Int) {
        val result = MathUtils().isPrime(n)

        assertFalse(result)
    }

    @ParameterizedTest(name = "isPrime | if prime then true: {0}")
    @ValueSource(ints = [2, 3, 7, 11, 101])
    fun `if prime then true`(n: Int) {
        val result = MathUtils().isPrime(n)

        assertTrue(result)
    }

    @ParameterizedTest(name = "fibonacciNumbers | if inappropriate n then empty array: {0}")
    @ValueSource(ints = [-1, 0])
    fun `if inappropriate n then empty array`(n: Int) {
        val result = MathUtils().fibonacciNumbers(n)

        assertArrayEquals(result, intArrayOf())
    }

    @Test
    fun `array of 1 fibonacci number`() {
        val result = MathUtils().fibonacciNumbers(1)

        assertArrayEquals(result, intArrayOf(0))
    }

    @Test
    fun `array of n fibonacci numbers`() {
        val result = MathUtils().fibonacciNumbers(8)

        assertArrayEquals(result, intArrayOf(0, 1, 1, 2, 3, 5, 8, 13))
    }
}
