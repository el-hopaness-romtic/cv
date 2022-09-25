import data.SongMetadata

class SongMetadataDAO {
    private val data: List<SongMetadata> = listOf(
        SongMetadata(1, "Them vs. You vs. Me", "Finger Eleven"),
        SongMetadata(2, "Blurryface", "Twenty One Pilots"),
        SongMetadata(3, "Blurryface", "Twenty One Pilots"),
        SongMetadata(4, "Random Access Memories", "Daft Punk"),
        SongMetadata(5, "Random Access Memories", "Daft Punk"),
        SongMetadata(6, "Discovery ", "Daft Punk"),
        SongMetadata(7, "Unknown Pleasures", "Joy Division"),
        SongMetadata(8, "Splinter", "The Offspring"),
        SongMetadata(9, "Smash", "The Offspring"),
        SongMetadata(10, "Ixnay on the Hombre", "The Offspring"),
        SongMetadata(11, "\"8-Bit Rebellion!\" OST", "Linkin Park"),
        SongMetadata(12, "Meteora", "Linkin Park"),
        SongMetadata(13, "<single>", "The Unlikely Candidates"),
        SongMetadata(14, "Dear Agony", "Breaking Benjamin"),
        SongMetadata(15, "Phobia", "Breaking Benjamin"),
        SongMetadata(16, "Phobia", "Breaking Benjamin")
    )

    fun getAllSongsMetadata(): List<SongMetadata> = data
    fun getSongMetadata(id: Int): SongMetadata? = data.find { it.id == id }
}
