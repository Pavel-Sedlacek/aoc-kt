package utils

import utils.common.times
import utils.readers.Reader


interface Day<R> {

    private fun day() = this.javaClass.simpleName.takeLast(2).toInt()
    private fun year() = this.javaClass.packageName.takeLast(4).toInt()

    private val printOffset
        get() = "   "

    val file: String
        get() = Reader.readFile(day(), year())

    fun runAll()

    fun run(p1: () -> R?, p2: () -> R?) {
        val c = Timer()
        c.start()
        println("$printOffset Day ${day()}")
        val p1s = p1()
        val p2s = p2()
        println("$printOffset ${c.elapsed()} ms")

        print("${printOffset * 2}part one: $p1s")
        print("${printOffset * 2}part two: $p2s")
        println()
    }

    fun run(p1: R?, p2: R?) = run({ p1 }, { p2 })
}