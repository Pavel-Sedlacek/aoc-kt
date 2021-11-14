package utils

import utils.readers.Reader


interface Day<R> {

    private val printOffset
        get() = "   "

    val file: String
        get() = Reader.readFile(
            this.javaClass.simpleName.takeLast(2).toInt(),
            this.javaClass.packageName.takeLast(4).toInt()
        )

    fun runAll()

    fun run(p1: () -> R?, p2: () -> R?) {
        println("${printOffset}Day ${this.javaClass.simpleName.takeLast(2)}")

        print("${printOffset * 2}part one: ")
        println(p1())
        print("${printOffset * 2}part two: ")
        println(p2())
        println()
    }
}