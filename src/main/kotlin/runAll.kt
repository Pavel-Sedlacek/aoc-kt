import y2021.Exec2021

@OptIn(ExperimentalStdlibApi::class)
fun main() {
    listOf(
//        Exec2015,
//        Exec2017,
//        Exec2020,
        Exec2021,
    ).onEach { it.execute(); repeat(3) { println() } }
}