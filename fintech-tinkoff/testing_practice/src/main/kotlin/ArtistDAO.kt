data class Artist(val id: Int, val name: String, val foundationYear: Int)

interface ArtistDAO {
    fun getArtistByID(id: Int): Artist?
    fun getAllArtists(): List<Artist>
}
