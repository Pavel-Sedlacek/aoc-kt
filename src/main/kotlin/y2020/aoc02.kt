package y2020

import java.io.File

fun main() {
    val input = File("src/main/resources/Day02.txt").useLines { it.toList() }
    println("Day TWO : check if letter occurrences in string matches range")
    println("Part One ${ partOneTwo(input) }")
    println("Part Two ${partTwoTwo(input)}")
}

class Day2 {
    fun run() {
        val input = File("src/main/resources/Day02.txt").useLines { it.toList() }
        println("Day TWO : check if letter occurrences in string matches range")
        println("Part One ${ partOneTwo(input) }")
        println("Part Two ${partTwoTwo(input)}")
    }
}

fun partOneTwo(x: List<String>): Int =
    x.count { i ->
        val min = i.substringBefore("-").toInt()
        val max = i.substringAfter("-").substringBefore(" ").toInt()
        val letter = i.substringAfter(" ").dropLast(1)
        val password = i.substringAfter(": ")
        (password.count { letter.contains(it) } in min..max)
    }

fun partTwoTwo(x: List<String>): Int =
    x.count { i ->
        val min = i.substringBefore("-").toInt()
        val max = i.substringAfter("-").substringBefore(" ").toInt()
        val letter = i.substringAfter(" ").dropLast(1)
        val password = i.substringAfter(": ")
        (password[min - 1] == letter[0] && password[max - 1] != letter[0]) || (password[min - 1] != letter[0] && password[max - 1] == letter[0])
    }