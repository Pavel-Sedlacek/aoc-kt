package utils

import java.io.File

class Utils {

    //region Reading
    fun read(year: Int, day: Int, mode: ReadMode): Any =
        when (mode) {
            ReadMode.BYTES -> this.readFileAsBytes(this.pathFor(day, year))
            ReadMode.LINES -> this.readFileAsLines(this.pathFor(day, year))
            ReadMode.STRING -> this.readFileAsString(this.pathFor(day, year))
            ReadMode.FLOATS -> this.readFileAsFloats(this.pathFor(day, year))
            ReadMode.INTS -> this.readFileAsInts(this.pathFor(day, year))
        }

    private fun readFileAsBytes(file: String): ByteArray = File(file).readBytes()

    private fun readFileAsFloats(file: String): List<Float> = File(file).useLines { it.toList() }.map { i -> i.toFloat() }

    private fun readFileAsInts(file: String): List<Int> = File(file).useLines { it.toList() }.map { i -> i.toInt() }

    private fun readFileAsLines(file: String): List<String> = File(file).useLines { it.toList() }

    private fun readFileAsString(file: String): String = File(file).readText()

    private val path: String = "src/main/resources/"

    private fun pathFor(day: Int, year: Int): String = "${this.path}/y$year/Day$day.txt"
    //endregion

    

}

enum class ReadMode {
    LINES, STRING, INTS, FLOATS, BYTES,
}