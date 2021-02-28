package y2020

import utils.Day

object Exec2020 {

    fun execute() {
        val days = listOf(Day01(), Day02(), Day03(), Day04(), Day05(), Day06(), Day07(), Day08(), Day09(), Day10(), Day11())

        for (day: Day in days)
            day.runAll()
    }
}

private fun main() {
    Exec2020.execute()
}

