import io.mockk.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ArtistDAOTest {

    private val artists: List<Artist> = listOf(
        Artist(1, "Snow Patrol", 1994),
        Artist(2, "Nickelback", 1995),
        Artist(3, "Hurts", 2009),
        Artist(4, "Imagine Dragons", 2008)
    )

    private val artistDAO = mockk<ArtistDAO> {
        every { getAllArtists() } returns artists
        every { getArtistByID(any()) } returns null
        every { getArtistByID(range(1, 4)) } answers {
            artists[firstArg() as Int - 1]
        }
    }

    @ParameterizedTest(name = "null if id is more than 4 or less than 1: {0}")
    @ValueSource(ints = [-1, 0, 5, 6])
    fun `null if id is more than 4 or less than 1`(id: Int) {
        assertEquals(null, artistDAO.getArtistByID(id))
    }

    @Test
    fun `testing getAll`() {
        assertEquals(artists, artistDAO.getAllArtists())
    }

    @Test
    fun `testing id from 1 to 4 and verifying search function call counter`() {
        for (i in 1..4) {
            assertEquals(artists[i - 1], artistDAO.getArtistByID(i))
        }

        verify(exactly = 4) { artistDAO.getArtistByID(any()) }
    }
}
