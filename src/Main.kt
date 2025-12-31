import kotlin.math.abs


fun main() {
    val genres = arrayOf("classic", "pop", "classic", "classic", "pop")
    val plays = intArrayOf(500, 600, 150, 800, 2500)
    solution(genres, plays).forEach { println(it) }
}

fun solution(genres: Array<String>, plays: IntArray): IntArray {
    val songsByGenre = genres.indices
        .map { Song(it, plays[it], genres[it]) }
        .groupBy { it.genre }
        .toList()
    return songsByGenre
        .sortedByDescending { (_, songs) -> songs.sumOf { it.playCount } }
        .flatMap { (_, songs) ->
            songs.sortedWith(compareByDescending<Song> { it.playCount }.thenBy { it.id })
                .take(2) // Take top 2
        }
        .map { it.id }
        .toIntArray()
}

data class Song(val id: Int, val playCount: Int, val genre:String)