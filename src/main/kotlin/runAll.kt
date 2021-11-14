import y2015.Exec2015
import y2017.Exec2017
import y2020.Exec2020

fun main() {
    listOf(
//        Exec2015,
        Exec2017,
        Exec2020
    ).onEach { it.execute(); repeat(3) { println()} }
}