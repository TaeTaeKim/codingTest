fun main() {
    val processes = intArrayOf(1, 1, 9, 1, 1, 1)
    val location = 0
    solution(processes, location)
}

fun solution(priorities: IntArray, location: Int): Int {
    val processQueue = ArrayDeque<Process>()
    priorities.forEachIndexed { idx, it -> processQueue.addLast(Process(idx, it)) }

    var runCount = 0

    while (true) {
        val maxPriority = processQueue.maxOf { it.priority }
        val currentProcess = processQueue.removeFirst()

        if (currentProcess.priority >= maxPriority) {
            runCount++
            if (location == currentProcess.id) {
                break;
            }
        } else {
            processQueue.addLast(currentProcess)
        }
    }
    println("runCount = ${runCount}")
    return runCount
}

data class Process(
    val id: Int,
    val priority: Int,
)