package y2015

import utils.Day


object Exec2015 {

    fun execute() {
        val days = listOf(
//            Day01(), Day02(), Day03(), Day04()
            Day05(), Day06()
        )

        for (day: Day in days)
            day.runAll()
    }
}

private fun main() {
    Exec2015.execute()
}
