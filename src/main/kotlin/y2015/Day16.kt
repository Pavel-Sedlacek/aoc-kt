package y2015

import utils.Day
import utils.Files
import utils.Helpers.log

enum class MatchResult {
    NO_MATCH,
    CAN_MATCH,
    PERFECT_MATCH
}

data class Matches(
    val children: Int,
    val cats: Int,
    val samoyeds: Int,
    val pomeranians: Int,
    val akitas: Int,
    val vizslas: Int,
    val goldfish: Int,
    val trees: Int,
    val cars: Int,
    val perfumes: Int
) {
    fun match(other: Matches): MatchResult {

        return MatchResult.NO_MATCH
    }

    companion object {
        fun from(let: Map<String, Int>): Matches {
            return Matches(
                children = let["children"] ?: -1,
                cats = let["cats"] ?: -1,
                samoyeds = let["samoyeds"] ?: -1,
                pomeranians = let["pomeranians"] ?: -1,
                akitas = let["akitas"] ?: -1,
                vizslas = let["vizslas"] ?: -1,
                goldfish = let["goldfish"] ?: -1,
                trees = let["trees"] ?: -1,
                cars = let["cars"] ?: -1,
                perfumes = let["perfumes"] ?: -1
            )
        }
    }
}

fun main() {
    Day16().runAll()
}

class Day16 : Day {

    private val aunts = Files.readFileAsLines(2015, 16).associate { line ->
        line.substringAfter(" ").substringBefore(" ").let {
            it.replace(":", "").toInt() to Matches.from(line.let { args ->
                args.substringAfter(" ").substringAfter(" ").split(",").associate { arg ->
                    arg.split(": ").let { th -> th[0].trim() to th[1].trim().toInt() }
                }
            })
        }
    }
    private val scan = Matches(3, 7, 2, 3, 0, 0, 5, 3, 2, 1)

    override fun runAll() {
        println("Day 16: aunt crime solving")
        println(partOne(aunts, scan))
    }

    private fun partOne(aunts: Map<Int, Matches>, scan: Matches) {
        aunts.filter { (aunt, aScan) ->
            aScan.match(scan).let {
                it != MatchResult.NO_MATCH
            }
        }.log()
    }

    private fun partTwo() {

    }
}