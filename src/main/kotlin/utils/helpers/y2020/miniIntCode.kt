package utils.helpers.y2020

fun recursiveMiniIntCode(x: List<String>): Int {
    val visitedLines = (0..x.size).toMutableSet()
    var accumulator = 0
    var i = 0
    while (i < x.size) {
        if (!visitedLines.contains(i)) return -1
        else visitedLines.remove(i)
        val currentLine = x[i].split(" ")
        when (currentLine[0]) {
            "acc" -> {
                accumulator += currentLine[1].toInt()
            }
            "jmp" -> {
                i += currentLine[1].toInt()
                continue
            }
        }
        i++
    }
    return accumulator
}
