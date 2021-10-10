package y2015

import utils.Day
import utils.Files

fun main() {
    Day14().runAll()
}

enum class State {
    RESTING,
    SPEEDING
}

data class Stats(
    val speed: Int,
    val speedDuration: Int,
    val restDuration: Int,
    var distanceTraveled: Int = 0,
    var restRemaining: Int = 0,
    var speedRemaining: Int = speedDuration,
    var state: State = State.SPEEDING,
    var score: Int = 0
) {
    fun move() {
        if (state == State.SPEEDING) {
            go()
        } else {
            stop()
        }
    }

    private fun go() {
        if (speedRemaining <= 0) {
            restRemaining = restDuration
            state = State.RESTING
            stop()
            return
        }
        distanceTraveled += speed
        speedRemaining--
    }

    private fun stop() {
        if (restRemaining <= 0) {
            speedRemaining = speedDuration
            state = State.SPEEDING
            go()
            return
        }
        restRemaining--
    }
}

class Day14 : Day {

    private val input = Files.readFileAsLines(2015, 14).map {
        it.split(" ").let { a ->
            a[0] to Stats(a[3].toInt(), a[6].toInt(), a[13].toInt())
        }
    }

    override fun runAll() {
        repeat(2503) { _ ->
            this.input.forEach { it.second.move() }
            this.input.maxByOrNull { it.second.distanceTraveled }!!.second.score++
        }
        println("Day 14: Reindeer speed")
        println(partOne(input))
        println(partTwo(input))
    }

    private fun partOne(input: List<Pair<String, Stats>>): Int = input.maxOf { it.second.distanceTraveled }

    private fun partTwo(input: List<Pair<String, Stats>>): Int = input.maxOf { it.second.score }
}