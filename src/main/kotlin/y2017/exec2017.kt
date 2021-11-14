package y2017

import utils.YearExecutor


object Exec2017: YearExecutor {

    override fun execute() {
        val days = listOf(
            Day01(), Day02(), Day03(), Day04(), Day05(), Day06(),
            Day15()
        )
        println("Year 2017")
        for (day in days)
            day.runAll()
    }
}
