package utils.helpers.y2021

class BingoBoard(boardRepresentation: String) {

    private val numbers = Array(5) { Array(5) { Number(0) } }

    init {
        boardRepresentation.split("\n").forEachIndexed { rowIndex, row ->
            row.split(" ").filterNot { it.isBlank() }.forEachIndexed { colIndex, number ->
                numbers[rowIndex][colIndex] = Number(number.toInt())
            }
        }
    }

    fun mark(value: Int) {
        numbers.forEach { row ->
            row.filter { number -> number.value == value }
                .onEach { number -> number.marked = true }
        }
    }

    fun hasWon(): Boolean {
        val hasWinningRow = numbers.any { row ->
            row.all { it.marked }
        }

        val hasWinningCol = (0 until 5).any { col ->
            numbers.map { row -> row[col] }.all { number -> number.marked }
        }

        return hasWinningRow || hasWinningCol
    }

    fun sumOfUnmarked(): Int {
        return numbers.sumOf { row ->
            row.filterNot { number -> number.marked }.sumOf { number -> number.value }
        }
    }

    private data class Number(val value: Int, var marked: Boolean = false)
}