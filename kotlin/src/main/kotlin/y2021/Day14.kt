package y2021

import utils.Day
import utils.helpers.y2021.polymerInsertionProcess

class Day14 : Day<Long> {

    private val input = file.split("\n\n")

    private val template = input.first()
    private val rules = input.drop(1)
        .groupBy({ it.substringBefore(" -> ") }, { it.substringAfter(" -> ") })
        .mapValues { it.value.single() }

    override fun runAll() = super.run({ partOne(template, rules) }, { partTwo(template, rules) })

    private fun partOne(template: String, insertions: Map<String, String>): Long =
        template.polymerInsertionProcess(10, insertions)

    private fun partTwo(template: String, insertions: Map<String, String>): Long =
        template.polymerInsertionProcess(40, insertions)
}

