package y2021

import utils.YearExecutor

object Exec2021 : YearExecutor {

    override fun execute() {
        val days = listOf(
            Day01()
        )

        println("Year 2021")

        for (day in days)
            day.runAll()
    }
}
