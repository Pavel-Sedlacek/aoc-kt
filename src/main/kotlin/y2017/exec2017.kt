package y2017

import Day15
import utils.Day


object Exec2017 {

    fun execute() {
        val days = listOf(
            Day15()
        )

        for (day: Day in days)
            day.runAll()
    }
}

private fun main() {
    Exec2017.execute()
}
