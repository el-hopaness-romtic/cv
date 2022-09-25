fun main() {
    val musicDB = MusicDB()
    musicDB.initialize()

    val service = DBService()
    service.use {
        println(it.getArtistById(8))
        println(it.getArtistById(-1))

        println("\n---------------\n")

        it.getArtistsByYearRange(1997, 1999).forEach { artist -> println(artist) }
        println("-----")
        it.getArtistsByYearRange(1444, 1506).forEach { artist -> println(artist) }

        println("\n---------------\n")

        it.getAlbumAndArtistById(15)?.let { pair -> println("${pair.first}\n${pair.second}") }
        println("-----")
        it.getAlbumAndArtistById(-1)?.let { pair -> println("${pair.first}\n${pair.second}") }

        println("\n---------------\n")

        it.countRecordLabelsContracts().forEach { pair -> println("${pair.first} -- ${pair.second}") }

        println("\n---------------\n")

        it.countRecordLabelsContractsDescending().forEach { pair -> println("${pair.first} -- ${pair.second}") }
    }

    musicDB.clean()
}
