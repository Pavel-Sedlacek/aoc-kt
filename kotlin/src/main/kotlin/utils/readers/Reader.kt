package utils.readers

import java.io.File

object Reader {
    private const val path: String = "src/main/resources/"
    fun readFile(day: Int, year: Int): String = File(pathFor(day, year)).readText()
    private fun pathFor(day: Int, year: Int): String = "$path/y$year/Day${if (day < 10) "0" else ""}$day.txt"
}



