fun main() {
    // Test Case 1: from Main.java
    val progresses1 = intArrayOf(93, 30, 55)
    val speeds1 = intArrayOf(1, 30, 5)
    val solution1 = solution(progresses1, speeds1)
    println("Result 1: ${solution1.contentToString()} (Expected: [2, 1])")

    println() // Separator

    // Test Case 2: from Main.java
    val progresses2 = intArrayOf(95, 90, 99, 99, 80, 99)
    val speeds2 = intArrayOf(1, 1, 1, 1, 1, 1)
    val solution2 = solution(progresses2, speeds2)
    println("Result 2: ${solution2.contentToString()} (Expected: [1, 3, 2])")
}

fun solution(progresses: IntArray, speeds: IntArray): IntArray {
    val leftDays = ArrayDeque<Int>()
    for (i in progresses.indices) {
        leftDays.addLast(getLeftDay(progresses[i], speeds[i]))
    }
    val deployCounts = mutableListOf<Int>()
    while(leftDays.isNotEmpty()) {
        var deployed = 1
        val needDay = leftDays.removeFirst()
        while(leftDays.isNotEmpty() && leftDays.first() <= needDay){
            leftDays.removeFirst()
            deployed++
        }
        deployCounts.add(deployed)

    }
    return deployCounts.toIntArray()
}

fun getLeftDay(progress:Int, speed: Int): Int{
    val leftDays = 100 - progress
    var toDeploy = leftDays / speed
    if(leftDays%speed != 0){
        toDeploy ++
    }
    return toDeploy
}
