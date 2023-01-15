import org.junit.jupiter.params.provider.ValueSource
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertEquals

class IsPalindromeTest {

    @ParameterizedTest(name = "if palindrome then true: {0}")
    @ValueSource(strings = ["ABBA", "777", "radar"])
    fun `if palindrome then true`(s: String) {
        val result = s.isPalindrome()

        assertTrue(result)
    }

    @ParameterizedTest(name = "if not palindrome then false: {0}")
    @ValueSource(strings = ["home", "035", "QWERTY"])
    fun `if not palindrome then false`(s: String) {
        val result = s.isPalindrome()

        assertFalse(result)
    }

    @Test
    fun `if empty string then exception`() {
        val exception = assertThrows(IllegalArgumentException::class.java) { "".isPalindrome() }

        assertEquals("String is empty", exception.message)
    }
}
