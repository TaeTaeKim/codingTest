package csvReader

import java.io.File

fun main(args: Array<String>) {
    val memberList = readCsv()

    // 취미별 인원 수
    val countByHobby = memberList.countByHobby()
    // 정씨의 취미별 개수
    val countByBobbyOnLastName = memberList.countByBobbyOnLastName("정")
    // 코멘트의 좋아요 개수
    val likeInCount = memberList.likeInCount()


}

fun List<Member>.countByHobby(): Map<String, Int> {
    return flatMap { it.hobby }
        .groupingBy { it }
        .eachCount()
}

fun List<Member>.countByBobbyOnLastName(lastname: String): Map<String, Int> {
    return filter { it.lastname.equals(lastname) }
        .flatMap { it.hobby }
        .groupingBy { it }
        .eachCount()
}

fun List<Member>.likeInCount():Int{
    return sumOf { it.likeInComment() }
}


fun readCsv(path: String = "resources/test.csv"): List<Member> {
    return File(path).useLines { lines ->
        lines.drop(1).map { line ->
            val row = line.split(",").map { it.trim() }
            Member(
                row[0],
                row[1].split(":"),
                row[2]
            )
        }.toList()
    }
}

data class Member(val name: String, val hobby: List<String>, val comment: String) {
    val lastname get() = name.first().toString()

    fun likeInComment(): Int = "좋아".toRegex().findAll(comment).count()
}