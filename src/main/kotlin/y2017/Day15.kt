package y2017

import utils.Day

class Day15 : Day<Int> {

    override fun runAll() = super.run({partOne()}, {-1})

    private fun partOne(): Int {
        var iterationA: Long = A.toLong()
        var iterationB: Long = B.toLong()

        var count = 0

        for (i in 0 until ITERATIONS) {
            if (iterationA bitEq iterationB) count++

            iterationA = (iterationA * A_FACTOR) % DIV_FACTOR
            iterationB = (iterationB * B_FACTOR) % DIV_FACTOR
        }
        return count
    }

    companion object {
        private const val A = 618
        private const val B = 814
        private const val A_FACTOR = 16807
        private const val B_FACTOR = 48271
        private const val DIV_FACTOR = 2147483647
        private const val ITERATIONS = 40_000_000
        private const val BIT_LENGTH = 32
    }

    private infix fun Long.bitEq(other: Long): Boolean {
        var a = this.toString(2)
        var b = other.toString(2)
        a = "0".repeat(BIT_LENGTH - a.length) + a
        b = "0".repeat(BIT_LENGTH - b.length) + b
        return (a.endsWith(b.substring(b.length - 16)))
    }
}


