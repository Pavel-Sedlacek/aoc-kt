import utils.log
import utils.readers.asLines
import java.io.File

fun main() {
    File("src/main/resources/y2017/Day02.txt").readText().asLines().map { it.split("\\s".toRegex())}.log()
}