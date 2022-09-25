import data.SongWithMetadata

class Helper(private val songDAO: SongDAO, private val songMetadataDAO: SongMetadataDAO) {
    fun getAllSongsWithMetadata(): List<SongWithMetadata> = songDAO.getAllSongs().map {
        val metadata = songMetadataDAO.getSongMetadata(it.id)
        SongWithMetadata(it.id, it.title, it.duration, metadata?.album ?: "", metadata?.artist ?: "")
    }

    fun <R : Comparable<R>> getAllSongsWithMetadataSortBy(selector: (SongWithMetadata) -> R?): List<SongWithMetadata> =
        getAllSongsWithMetadata().sortedBy(selector)

    fun <K> getAllSongsWithMetadataGroupBy(keySelector: (SongWithMetadata) -> K): Map<K, List<SongWithMetadata>> =
        getAllSongsWithMetadata().groupBy(keySelector)

    fun getSongsWithMetadataCount(predicate: (SongWithMetadata) -> Boolean): Int =
        getAllSongsWithMetadata().filter(predicate).count()
}
