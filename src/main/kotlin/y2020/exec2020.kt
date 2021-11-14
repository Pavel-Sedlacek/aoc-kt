package y2020

import utils.YearExecutor

object Exec2020 : YearExecutor {
    //TODO => 11, 16. 19, 20, 21, 22, 23, 24, 25

    override fun execute() {
        val days = listOf(Day01(), Day02(), Day03(), Day04(), Day05(), Day06(),
            Day07(), Day08(), Day09(), Day10(), Day11(), Day12(), Day13(),
            Day14(), /*y2017.Day15(),*/ Day16(), Day17(), Day18())

        println("Year 2020")

        for (day in days)
            day.runAll()
    }
}

private fun main() {
    Exec2020.execute()
}

