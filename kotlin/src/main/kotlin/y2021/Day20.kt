package y2021

import utils.Day
import utils.readers.asLines

class Day20 : Day<Int> {

    val input = file.asLines().map { row -> row.map { if (it == '#') '1' else '0' }.joinToString("") }

    private val rules = input.first().toList()
    private val image = input.drop(2)

    override fun runAll() = super.run({ partOne(image, rules) }) { partTwo(image, rules) }

    private fun partOne(input: List<String>, rules: List<Char>): Int =
        input.refine(rules, 2).sumOf { row -> row.count { it == '1' } }

    private fun partTwo(input: List<String>, rules: List<Char>): Int =
        input.refine(rules, 50).sumOf { row -> row.count { it == '1' } }

    private fun List<String>.refine(rules: List<Char>, steps: Int): List<String> {
        return (0 until steps).fold(this) { image, step ->
            val outside = when (rules.first() == '1') {
                true -> if (step % 2 == 0) rules.last() else rules.first()
                false -> '0'
            }
            (-1..image.size).map { y ->
                (-1..image.first().length).map { x ->
                    (-1..1).flatMap { dy -> (-1..1).map { dx -> dy to dx } }
                        .map { (dy, dx) -> y + dy to x + dx }
                        .joinToString("") { (y, x) -> (image.getOrNull(y)?.getOrNull(x) ?: outside).toString() }
                        .toInt(2).let { rules[it] }
                }.joinToString("")
            }
        }
    }
}