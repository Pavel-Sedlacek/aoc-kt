package utils

import java.io.File

object Files {


    //region READ
    fun readFileAsBytes(year: Int, day: Int): ByteArray = File(pathFor(day, year)).readBytes()

    fun readFileAsFloats(year: Int, day: Int): List<Float> = File(pathFor(day, year)).useLines { it.toList() }.map { i -> i.toFloat() }

    fun readFileAsInts(year: Int, day: Int): List<Int> = File(pathFor(day, year)).useLines { it.toList() }.map { i -> i.toInt() }

    fun readFileAsLines(year: Int, day: Int): List<String> = File(pathFor(day, year)).useLines { it.toList() }

    fun readFileAsMutableList(year: Int, day: Int): MutableList<String> = File(pathFor(day, year)).useLines { it.toMutableList()}

    fun readFileAsString(year: Int, day: Int): String = File(pathFor(day, year)).readText()

    fun readFileAsMutableLongs(year: Int, day: Int): MutableList<Long> = File(pathFor(day, year)).useLines { it.toList() }.map { it.toLong() }.toMutableList()

    fun readFileAsIntsDividedBy(year: Int, day: Int, separator: Regex): MutableList<Int> = File(pathFor(day, year)).readText().split(separator).map { it.toInt() }.toMutableList()

    fun readFileAsLinesSplitBy(year: Int, day: Int, separator: String): List<String> = File(pathFor(day, year)).readText().split(separator)

        const val path: String = "src/main/resources/"

    private fun pathFor(day: Int, year: Int): String = "${this.path}/y$year/Day${if (day < 10) "0" else ""}$day.txt"
    //endregion

}

