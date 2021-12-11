import y2021.Exec2021
import y2022.Exec2022

@OptIn(ExperimentalStdlibApi::class)
fun main() {
    listOf(
//        Exec2015,
//        Exec2017,
//        Exec2020,
        Exec2021,
//        Exec2022
    ).onEach { it.execute(); repeat(3) { println() } }
}