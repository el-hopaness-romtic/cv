import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import io.mockk.every
import io.mockk.mockk

class PointTest {

    @Test
    fun `testing mocks`() {
        val point = mockk<Point> {
            every { x } returns 3.0
            every { y } returns 4.0
            every { distance(Point()) } returns 5.0
        }

        assertAll(
            { assertEquals(3.0, point.x) },
            { assertEquals(4.0, point.y) },
            { assertEquals(5.0, point.distance(Point())) },
        )
    }
}
