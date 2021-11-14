package y2015

import utils.YearExecutor

object Exec2015 : YearExecutor {

    override fun execute() {
        val days = listOf(
            Day01(), Day02(), Day03(), Day04(), Day05(), Day06(), Day08(), Day09(), Day10()
        )
        println("2015")
        for (day in days) day.runAll()
    }
}

private fun main() {
    Exec2015.execute()
}
