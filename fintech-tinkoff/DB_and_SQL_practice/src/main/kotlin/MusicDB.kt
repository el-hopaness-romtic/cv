import java.io.File
import java.sql.Connection
import java.sql.DriverManager


class MusicDB {
    private val resourcesDir = File("./DB_and_SQL_practice/src/main/resources").apply { mkdir() }
    private val connection = DriverManager.getConnection("jdbc:sqlite:./DB_and_SQL_practice/src/main/resources/db")

    fun initialize() {
        connection.let {
            createRecordLabels(it)
            createArtists(it)
            createAlbums(it)
            createArtistsRecordLabels(it)
        }
    }

    fun clean() {
        connection.use {
            with(it.createStatement()) {
                execute("DROP TABLE record_labels")
                execute("DROP TABLE artists")
                execute("DROP TABLE albums")
                execute("DROP TABLE artists_record_labels")
            }
        }

        resourcesDir.deleteRecursively()
    }

    private fun createRecordLabels(connection: Connection) {
        connection.createStatement().use {
            it.execute(
                """
                    CREATE TABLE record_labels(
                        record_label_id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT,
                        is_active INTEGER
                    )
                    """
            )

            it.execute(
                """
                    INSERT INTO record_labels(name, is_active) 
                    VALUES
                        ('Underground Symphony', 1),
                        ('Black Lodge Records', 1),
                        ('Nuclear Blast', 1),
                        ('Jive Records', 0),
                        ('RCA Records', 1),
                        ('EMI America Records', 0),
                        ('Enigma Records', 0),
                        ('Warner Records', 1),
                        ('Atlantic Records', 1),
                        ('Capitol Records', 1),
                        ('Factory Records', 0),
                        ('Giant Records', 1),
                        ('Sumerian Records', 1),
                        ('Lookout! Records', 0),
                        ('Reprise Records', 1),
                        ('Machine Shop Records', 1),
                        ('Hollywood Records', 1),
                        ('Republic Records', 1),
                        ('American Recordings', 1),
                        ('Columbia Records', 1)
                    """
            )
        }
    }

    private fun createArtists(connection: Connection) {
        connection.createStatement().use {
            it.execute(
                """
                    CREATE TABLE artists(
                        artist_id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT,
                        foundation_year INTEGER
                    )
                    """
            )

            it.execute(
                """
                    INSERT INTO artists(name, foundation_year) 
                    VALUES
                        ('Breaking Benjamin', 1999),
                        ('System of a Down', 1994),
                        ('Green Day', 1987),
                        ('Linkin Park', 1996),
                        ('Asking Alexandria', 2006),
                        ('Joy Division', 1976),
                        ('Avenged Sevenfold', 1999),
                        ('Bullet for My Valentine', 1998),
                        ('Sabaton', 1999),
                        ('Red Hot Chili Peppers', 1983),
                        ('Three Days Grace', 1997),
                        ('Bruno Mars', 2004),
                        ('Skillet', 1996),
                        ('Coldplay', 1996)
                    """
            )
        }
    }

    private fun createAlbums(connection: Connection) {
        connection.createStatement().use {
            it.execute(
                """
                    CREATE TABLE albums(
                        album_id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT,
                        artist_id INTEGER,
                        FOREIGN KEY(artist_id) REFERENCES artists(artist_id)
                    )
                    """
            )

            it.execute(
                """
                    INSERT INTO albums(name, artist_id)
                    VALUES
                        ('We Are Not Alone', 1),
                        ('Dear Agony', 1),
                        ('Toxicity', 2),
                        ('Hypnotize', 2),
                        ('American Idiot', 3),
                        ('Hybrid Theory', 4),
                        ('Minutes to Midnight', 4),
                        ('One More Light', 4),
                        ('Stand Up and Scream', 5),
                        ('From Death to Destiny', 5),
                        ('Unknown Pleasures', 6),
                        ('City of Evil', 7),
                        ('Hail to the King', 7),
                        ('Primo Victoria', 9),
                        ('The Art of War', 9),
                        ('Carolus Rex', 9),
                        ('Californication', 10),
                        ('I’m with You', 10),
                        ('One-X', 11),
                        ('Outsider', 11),
                        ('Scream Aim Fire', 8),
                        ('Temper Temper', 8),
                        ('Unorthodox Jukebox', 12),
                        ('Collide', 13),
                        ('Victorious', 13),
                        ('Viva la Vida or Death and All His Friends', 14),
                        ('Everyday Life', 14)
                    """
            )
        }
    }

    private fun createArtistsRecordLabels(connection: Connection) {
        connection.createStatement().use {
            it.execute(
                """
                    CREATE TABLE artists_record_labels(
                        record_label_id INTEGER,
                        artist_id INTEGER,
                        FOREIGN KEY(record_label_id) REFERENCES record_labels(record_label_id),
                        FOREIGN KEY(artist_id) REFERENCES artists(artist_id)
                    )
                    """
            )

            it.execute(
                """
                    INSERT INTO artists_record_labels(record_label_id, artist_id)
                        VALUES
                            (17, 1),
                            (19, 2),
                            (20, 2),
                            (14, 3),
                            (15, 3),
                            (8, 4),
                            (13, 5),
                            (11, 6),
                            (8, 7),
                            (5, 8),
                            (2, 9),
                            (3, 9),
                            (6, 10),
                            (8, 10),
                            (4, 11),
                            (5, 11),
                            (9, 12),
                            (9, 13),
                            (6, 14),
                            (10, 14),
                            (9, 14)
                    """
            )
        }
    }
}
