import data.Album
import data.Artist
import data.RecordLabel

class DBService : AutoCloseable {
    private val database = DBClient("jdbc:sqlite:./DB_and_SQL_practice/src/main/resources/db")
    override fun close() = database.close()

    fun getArtistById(artistID: Int): Artist? =
        database.getPreparedStatement(
            """
            SELECT artist_id, name, foundation_year
            FROM artists
            WHERE artist_id = ?
            """,
            artistID
        ).use { stm ->
            stm.executeQuery().takeIf { it.next() }?.let {
                Artist(
                    it.getInt(1),
                    it.getString(2),
                    it.getInt(3)
                )
            }
        }


    fun getArtistsByYearRange(from: Int, to: Int): List<Artist> {
        database.getPreparedStatement(
            """
            SELECT artist_id, name, foundation_year
            FROM artists
            WHERE ? <= foundation_year AND foundation_year <= ?
            """,
            from, to
        ).use { stm ->
            stm.executeQuery().let {
                val artists = mutableListOf<Artist>()
                while (it.next()) {
                    artists += Artist(
                        it.getInt(1),
                        it.getString(2),
                        it.getInt(3)
                    )
                }

                return artists
            }
        }
    }

    fun getAlbumAndArtistById(albumID: Int): Pair<Artist, Album>? =
        database.getPreparedStatement(
            """
            SELECT artists.artist_id, artists.name, artists.foundation_year,
                   albums.album_id, albums.name, albums.artist_id
            FROM artists LEFT JOIN albums
            ON artists.artist_id = albums.artist_id
            WHERE albums.album_id = ?
            """,
            albumID
        ).use { stm ->
            stm.executeQuery().takeIf { it.next() }?.let {
                Pair(
                    Artist(it.getInt(1), it.getString(2), it.getInt(3)),
                    Album(it.getInt(4), it.getString(5), it.getInt(6))
                )
            }
        }


    fun countRecordLabelsContracts(): List<Pair<RecordLabel, Int>> {
        database.getPreparedStatement(
            """
            SELECT record_labels.record_label_id, record_labels.name, record_labels.is_active, COUNT(artist_id)
            FROM artists_record_labels LEFT JOIN record_labels
            ON artists_record_labels.record_label_id = record_labels.record_label_id
            GROUP BY record_labels.record_label_id
            """
        ).use { stm ->
            stm.executeQuery().let {
                val pairs = mutableListOf<Pair<RecordLabel, Int>>()
                while (it.next()) {
                    pairs +=
                        Pair(
                            RecordLabel(it.getInt(1), it.getString(2), it.getInt(3) == 1),
                            it.getInt(4)

                        )
                }

                return pairs
            }
        }
    }

    fun countRecordLabelsContractsDescending(): List<Pair<RecordLabel, Int>> {
    database.getPreparedStatement(
            """
            SELECT record_labels.record_label_id, record_labels.name, record_labels.is_active, COUNT(artist_id)
            FROM artists_record_labels LEFT JOIN record_labels
            ON artists_record_labels.record_label_id = record_labels.record_label_id
            GROUP BY record_labels.record_label_id
            ORDER BY COUNT(artist_id) DESC
            """
        ).use { stm ->
            stm.executeQuery().let {
                val pairs = mutableListOf<Pair<RecordLabel, Int>>()
                while (it.next()) {
                    pairs.add(
                        Pair(
                            RecordLabel(it.getInt(1), it.getString(2), it.getInt(3) == 1),
                            it.getInt(4)
                        )
                    )
                }

                return pairs
            }
        }
    }
}
