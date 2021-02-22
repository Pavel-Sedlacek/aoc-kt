package y2020

import java.io.File
import kotlin.math.ceil

fun main() {
    val input = File("src/main/resources/Day13.txt").readText(Charsets.UTF_8).split("\n")
    println("Day THIRTEEN : Busses")
    println("Part One ${partOneThirteen(input)}")
    println("Part Two ${partTwoThirteen(input[1].split(","))}")
}

class Day13 {
    fun run() {
        val input = File("src/main/resources/Day13.txt").readText(Charsets.UTF_8).split("\n")
        println("Day THIRTEEN : Busses")
        println("Part One ${partOneThirteen(input)}")
        println("Part Two ${partTwoThirteen(input[1].split(","))}")
    }
}

fun partTwoThirteen(s: List<String>) : Long {
    val rem = mutableListOf<Int>(); val bus = mutableListOf<Int>()
    for (i in s.indices) {
        if (s[i] != "x") {
            rem.add(i)
            bus.add(s[i].toInt())
        }
    }
    var step = bus[0].toLong()
    var nextBus = 1; var timeStamp = 0L
    do {
        timeStamp += step
        if ((timeStamp + rem[nextBus]) % bus[nextBus] == 0L)
            step = lcm(step, bus[nextBus++].toLong())
    } while (nextBus < bus.size)
    return timeStamp
}

fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)
fun lcm(a: Long, b: Long): Long = a / gcd(a, b) * b

fun partOneThirteen(input : List<String>) : Long {
    val time = input[0].toInt()
    val jes = input[1].split(",").toMutableList()
    jes.removeAll { it == "x" }
    val buses = jes.toMutableList()

    for (i in 0 until buses.size) {
        buses[i] = ((ceil(((time/(buses[i].toInt())).toDouble())) + 1) * buses[i].toInt()).toString()
    }
    return ((buses.minOrNull()!!.substringBefore(".").toLong() - time.toLong()) * jes[2].toLong())
}