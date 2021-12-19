package utils.helpers.y2021

fun List<List<Int>>.basin(rowIdx: Int, colIdx: Int): List<Pair<Int, Int>> {
    return neighbours(this, rowIdx, colIdx)
        .filterNot { (x, y) -> this[x][y] == 9 }
        .fold(listOf((rowIdx to colIdx))) { points, (x, y) ->
            points + if (this[x][y] - this[rowIdx][colIdx] >= 1) this.basin(x, y) else emptyList()
        }
}

fun List<List<Int>>.lowPoints(): List<Pair<Int, Int>> {
    return this.foldIndexed(emptyList()) { rowIdx, allPoints, row ->
        row.foldIndexed(allPoints) { colIdx, points, height ->
            neighbours(this, rowIdx, colIdx)
                .all { (x, y) -> this[x][y] > height }
                .let { isLowest -> if (isLowest) points + (rowIdx to colIdx) else points }
        }
    }
}

fun neighbours(input: List<List<Int>>, rowIdx: Int, colIdx: Int): List<Pair<Int, Int>> {
    return arrayOf((-1 to 0), (1 to 0), (0 to -1), (0 to 1))
        .map { (dx, dy) -> rowIdx + dx to colIdx + dy }
        .filter { (x, y) -> x in input.indices && y in input.first().indices }
}