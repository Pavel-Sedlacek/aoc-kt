package y2020

import java.io.File

fun main() {
    val input = File("src/main/resources/Day05.txt").useLines { it.toMutableList() }
    for (i in 0 until input.size) {
        input[i] = input[i].replace("F", "0").replace("B", "1").replace("L", "0").replace("R", "1")
    }
    println("Day FIVE : plane seats")
    println("Part One ${partOneFive(input)}")
    println("Part One ${partTwoFive(input)}")
}

class Day5 {
    fun run() {
        val input = File("src/main/resources/Day05.txt").useLines {it.toMutableList()}
        for (i in 0 until input.size) {
            input[i] = input[i].replace("F", "0").replace("B", "1").replace("L", "0").replace("R", "1")
        }
        println("Day FIVE : plane seats")
        println("Part One ${partOneFive(input)}")
        println("Part One ${partTwoFive(input)}")
    }
}

fun partOneFive(x: MutableList<String>): Int =
    x.fold(0) { max, element ->
        val iNumber = Integer.parseInt(element.substring(0, 7), 2) * 8 + Integer.parseInt(element.substring(7),2)
        if (iNumber > max) iNumber
        else max
    }

fun partTwoFive(x: MutableList<String>): Int {
    val z = (55..890).toMutableList()
    x.forEach { i ->
        z.remove(
            Integer.parseInt((i.substring(0, 7)), 2) * 8 + Integer.parseInt(i.substring(7,i.length), 2)
        )
    }
    return z[0]
}