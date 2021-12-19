package y2021

import utils.Day
import utils.helpers.y2021.BingoBoard
import utils.readers.asLinesSplitBy

class Day04 : Day<Int> {

    private val input = file.asLinesSplitBy("\n\n")
    private val numbers: List<Int> = input[0].split(",").map { it.toInt() }
    private val boards: List<BingoBoard> = input.drop(1).map { BingoBoard(it) }

    override fun runAll() = super.run({ partOne(numbers, boards) }) { partTwo(numbers, boards) }

    fun partOne(numbers: List<Int>, boards: List<BingoBoard>): Int {
        numbers.forEach { number ->
            boards.onEach { board -> board.mark(number) }
                .firstOrNull { it.hasWon() }
                ?.let { winningBoard ->
                    return number * winningBoard.sumOfUnmarked()
                }
        }
        throw Exception()
    }

    fun partTwo(numbers: List<Int>, boards: List<BingoBoard>): Int {
        numbers.fold(boards) { boardAccumulator, number ->
            if (boardAccumulator.size == 1) {
                boardAccumulator.first().apply { mark(number) }.takeIf { it.hasWon() }?.let {
                    return number * it.sumOfUnmarked()
                }
            }
            boardAccumulator.onEach { board -> board.mark(number) }.filterNot { it.hasWon() }
        }
        throw Exception()
    }
}
