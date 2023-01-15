import data.Song

class SongDAO {
    private val data: List<Song> = listOf(
        Song(1, "Paralyzer", "3:36"),
        Song(2, "Stressed Out", "3:22"),
        Song(3, "Ride", "3:34"),
        Song(4, "Giorgio by Moroder", "9:04"),
        Song(5, "Get Lucky", "6:07"),
        Song(6, "Harder, Better, Faster, Stronger", "3:45"),
        Song(7, "Disorder", "3:28"),
        Song(8, "The Noose", "3:18"),
        Song(9, "Self-Esteem", "4:17"),
        Song(10, "Gone Away", "4:27"),
        Song(11, "Blackbirds", "3:21"),
        Song(12, "From the Inside", "2:53"),
        Song(13, "Novocaine", "4:15"),
        Song(14, "Hopeless", "3:20"),
        Song(15, "Unknown Soldier", "3:46"),
        Song(16, "Until the End", "4:12")
    )

    fun getAllSongs(): List<Song> = data
}
