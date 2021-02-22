package y2020

import java.io.File

fun main() {
    val input = File("src/main/resources/Day06.txt").readText().split("\\n\\n".toRegex())
    println("Day SIX : yes answer count")
    println("Part One ${partOneSix(input)}")
    println("Part Two ${partTwoSix(input)}")
}

class Day6 {
    fun run() {
        val input = File("src/main/resources/Day06.txt").readText().split("\\n\\n".toRegex())
        println("Day SIX : yes answer count")
        println("Part One ${partOneSix(input)}")
        println("Part Two ${partTwoSix(input)}")
    }
}

fun partOneSix(input: List<String>): Int {
    var sum = 0
    for (i in input) {
        val j = mutableSetOf<Char>()
        for (o in i) if (o in 'a'..'z') j += o
        sum += j.size
    }
    return sum
}

fun partTwoSix(input: List<String>): Int {
    var sum = 0

    for (i in input) {
        val j = mutableListOf<String>()
        var indexer = 0
        var adding = ""
        for (z in i) {
            if (z in 'a'..'z') {
                adding += z
            } else {
                j.add(adding)
                indexer++
                adding = ""
            }
        }
        j.add(adding)
        val letters = ('a'..'z').toMutableSet()

        for (h in j) {
            for (b in 'a'..'z') {
                if (!h.contains(b)) {
                    letters.remove(b)
                }
            }
        }
        sum += letters.size
    }
    return (sum)
}
