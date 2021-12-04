package y2021

import utils.BingoBoard
import utils.Day
import utils.readers.asLinesSplitBy

class Day04 : Day<Int> {

    private val input = file.asLinesSplitBy("\n\n")
    private val numbers = input[0].split(",").map { it.toInt() }
    private val boards = input.subList(1, input.size).map {
        it.trim().lines().map { it.trim().split("\\s+".toRegex()).map { it.trim().toIntOrNull() }.toMutableList() }
    }.map { BingoBoard(it) }

    override fun runAll() = super.run({ partOne(numbers, boards) }, { partTwo(numbers, boards) })

    private fun partOne(numbers: List<Int>, boards: List<BingoBoard>): Int {
        boards.onEach { it.reset() }
        for (i in numbers) {
            boards.forEach { it.flip(i) }
            val x = boards.find { it.isWinning() }
            if (x != null)
                return x.sum() * i
        }
        return 0
    }

    private fun partTwo(numbers: List<Int>, boards: List<BingoBoard>): Int {
        var loosingBoard: BingoBoard? = null
        for (i in numbers) {
            boards.forEach { it.flip(i) }
            if (loosingBoard?.isWinning() == true) return (loosingBoard?.sum() ?: 0) * i
            if (loosingBoard == null) loosingBoard = boards.singleOrNull { !it.isWinning() }
        }
        return 0
    }
}




