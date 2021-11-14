package y2020

import java.io.File
import java.util.*
import kotlin.math.roundToInt
import kotlin.math.sqrt

fun main() {
    val input = File("src/main/resources/Day20.txt").readText()
        .trimEnd()
        .split("\n\n")
        .map { it.split("\n") }
        .map {
            Tile(
                it.first().split(" ").last().dropLast(1).toLong(),
                it.drop(1).map { s -> s.map { c -> c == '#' } })
        }
    println("Day TWENTY : y wud u du dis ? (satellite picture castling)")
    println("Part One ${partOneTwenty(arrangeTilesOne(input))}")
    println("Part Two ${calcWaterRoughness(arrangeTilesTwo(input))}")
}

class Day20 {
    fun run() {
        val input = File("src/main/resources/Day20.txt").readText()
            .trimEnd()
            .split("\n\n")
            .map { it.split("\n") }
            .map {
                Tile(
                    it.first().split(" ").last().dropLast(1).toLong(),
                    it.drop(1).map { s -> s.map { c -> c == '#' } })
            }
        println("Day TWENTY : y wud u du dis ? (satellite picture castling)")
        println("Part One ${partOneTwenty(arrangeTilesOne(input))}")
        println("Part Two ${calcWaterRoughness(arrangeTilesTwo(input))}")
    }
}

private fun partOneTwenty(image: Array<Array<Tile>>): Long {
    return image.first().first().id * image.first().last().id * image.last().first().id * image.last().last().id
}

private fun arrangeTilesOne(tiles: List<Tile>): Array<Array<Tile>> {
    val allTiles = tiles.associateBy { it.id }
    val checkedTiles = mutableListOf<Tile>()
    val remainingTiles = tiles.toMutableList()

    val tileStack = Stack<Tile>()
    tileStack.push(tiles.first())

    while (tileStack.isNotEmpty()) {
        val currentTile = tileStack.pop()
        remainingTiles -= currentTile
        val currentBorders = currentTile.getAllBorders()

        remainingTiles.forEach { tile ->
            tile.getAllBorders().forEachIndexed { iOther, border ->
                if (border in currentBorders) {
                    val iCurrent = currentBorders.indexOf(border)
                    val rotations = ((iCurrent + 4) - iOther.opposite()) % 4
                    if (currentTile.getConnection(iCurrent) == null) {
                        allTiles[tile.id]!!.rotateRight(rotations)
                        if (iCurrent == 0 || iCurrent == 2) allTiles[tile.id]!!.flipHorizontal()
                        else allTiles[tile.id]!!.flipVertical()
                        currentTile.setConnection(iCurrent, tile.id)
                        tile.setConnection(iCurrent.opposite(), currentTile.id)
                        tileStack.push(tile)
                    }
                } else if (border.reversed() in currentBorders) {
                    val iCurrent = currentBorders.indexOf(border.reversed())
                    val rotations = ((iCurrent + 4) - iOther.opposite()) % 4
                    if (currentTile.getConnection(iCurrent) == null) {
                        allTiles[tile.id]!!.rotateRight(rotations)
                        currentTile.setConnection(iCurrent, tile.id)
                        tile.setConnection(iCurrent.opposite(), currentTile.id)
                        tileStack.push(tile)
                    }
                }
            }
        }

        checkedTiles += currentTile
    }

    val image = Array(sqrt(tiles.size.toDouble()).roundToInt()) {
        Array(sqrt(tiles.size.toDouble()).roundToInt()) {
            Tile(
                -1L,
                listOf()
            )
        }
    }
    var left: Tile? =
        allTiles.values.find { it.left == null && it.top == null } ?: error("Top left corner tile not found")

    for (y in image.indices) {
        var right: Tile? = left!!
        for (x in image[y].indices) {
            image[y][x] = right!!
            right = allTiles[right.right]
        }
        left = allTiles[left.bottom]
    }

    return image
}

private fun arrangeTilesTwo(tiles: List<Tile>): Tile {
    val allTiles = tiles.associateBy { it.id }
    val checkedTiles = mutableListOf<Tile>()
    val remainingTiles = tiles.toMutableList()

    val tileStack = Stack<Tile>()
    tileStack.push(tiles.first())

    while (tileStack.isNotEmpty()) {
        val currentTile = tileStack.pop()
        remainingTiles -= currentTile
        val currentBorders = currentTile.getAllBorders()

        remainingTiles.forEach { tile ->
            tile.getAllBorders().forEachIndexed { iOther, border ->
                if (border in currentBorders) {
                    val iCurrent = currentBorders.indexOf(border)
                    val rotations = ((iCurrent + 4) - iOther.opposite()) % 4
                    if (currentTile.getConnection(iCurrent) == null) {
                        allTiles[tile.id]!!.rotateRight(rotations)
                        if (iCurrent == 0 || iCurrent == 2) allTiles[tile.id]!!.flipHorizontal()
                        else allTiles[tile.id]!!.flipVertical()
                        currentTile.setConnection(iCurrent, tile.id)
                        tile.setConnection(iCurrent.opposite(), currentTile.id)
                        tileStack.push(tile)
                    }
                } else if (border.reversed() in currentBorders) {
                    val iCurrent = currentBorders.indexOf(border.reversed())
                    val rotations = ((iCurrent + 4) - iOther.opposite()) % 4
                    if (currentTile.getConnection(iCurrent) == null) {
                        allTiles[tile.id]!!.rotateRight(rotations)
                        currentTile.setConnection(iCurrent, tile.id)
                        tile.setConnection(iCurrent.opposite(), currentTile.id)
                        tileStack.push(tile)
                    }
                }
            }
        }

        checkedTiles += currentTile
    }

    val image = Array(sqrt(tiles.size.toDouble()).roundToInt()) {
        Array(sqrt(tiles.size.toDouble()).roundToInt()) {
            Tile(
                -1L,
                listOf()
            )
        }
    }
    var left: Tile? =
        allTiles.values.find { it.left == null && it.top == null } ?: error("Top left corner tile not found")

    for (y in image.indices) {
        var right: Tile? = left!!
        for (x in image[y].indices) {
            image[y][x] = right!!
            right = allTiles[right.right]
        }
        left = allTiles[left.bottom]
    }

    return Tile(0, image.map { row ->
        row.map { tile ->
            tile.removeBorder()
            tile.grid
        }.flatMap { it.mapIndexed { i, list -> IndexedValue(i, list) } }
            .groupBy({ (i, _) -> i }, { (_, v) -> v })
            .map { (_, v) -> v.reduce { acc, list -> acc + list } }
    }.reduce { acc, list -> acc + list })
}

private fun Int.opposite(): Int {
    return when (this) {
        0 -> 2
        1 -> 3
        2 -> 0
        3 -> 1
        else -> 0
    }
}

private fun calcWaterRoughness(image: Tile): Int {
    val monsters = findAllSeaMonsters(image)
    return if (monsters != 0) {
        image.grid.sumOf { r -> r.count { it } } - monsters * 15
    } else {
        -1
    }
}

private fun findAllSeaMonsters(image: Tile): Int {
    repeat(4) {
        countSeaMonsters(image).let { if (it != 0) return it }

        image.flipVertical()
        countSeaMonsters(image).let { if (it != 0) return it }

        image.flipHorizontal()
        countSeaMonsters(image).let { if (it != 0) return it }

        image.flipVertical()
        countSeaMonsters(image).let { if (it != 0) return it }

        image.flipHorizontal()

        image.rotateRight()
    }

    return 0
}

private fun countSeaMonsters(image: Tile): Int {
    var count = 0
    for (y in 1 until image.grid.size - 1) {
        for (x in 0 until image.grid.size - 19) {
            if (isSeaMonster(image, x, y)) count++
        }
    }

    return count
}

private fun isSeaMonster(image: Tile, x: Int, y: Int): Boolean {
    return if (image[x, y]) {
        (image[x + 18, y - 1] && image[x + 5, y] && image[x + 6, y] && image[x + 11, y] && image[x + 12, y] && image[x + 17, y] && image[x + 18, y] && image[x + 19, y] && image[x + 1, y + 1] && image[x + 4, y + 1] && image[x + 7, y + 1] && image[x + 10, y + 1] && image[x + 13, y + 1] && image[x + 16, y + 1])
    } else false
}

class Tile(val id: Long, var grid: List<List<Boolean>>) {

    // tile connections
    var top: Long? = null
    var right: Long? = null
    var bottom: Long? = null
    var left: Long? = null

    operator fun get(x: Int, y: Int): Boolean = grid[y][x]

    fun removeBorder() {
        grid = grid.drop(1).dropLast(1).map { it.drop(1).dropLast(1) }
    }

    fun getAllBorders(): List<List<Boolean>> {
        val borders = mutableListOf<List<Boolean>>()

        val top = grid.first()
        borders.add(top)
        // borders.add(top.reversed())

        val right = grid.map { it.last() }
        borders.add(right)
        // borders.add(right.reversed())

        val bottom = grid.last().reversed()
        borders.add(bottom)
        // borders.add(bottom.reversed())

        val left = grid.map { it.first() }.reversed()
        borders.add(left)
        // borders.add(left.reversed())

        return borders
    }

    fun rotateRight(times: Int = 1) {   // rotate in place
        repeat(times) {
            grid =
                grid.flatMap { it.withIndex() }.groupBy({ (i, _) -> i }, { (_, v) -> v }).map { (_, v) -> v.reversed() }
        }
    }

    fun flipHorizontal() {
        grid = grid.map { it.reversed() }
    }

    fun flipVertical() {
        grid = grid.reversed()
    }

    fun setConnection(i: Int, tileID: Long) {
        when (i) {
            0 -> top = tileID
            1 -> right = tileID
            2 -> bottom = tileID
            3 -> left = tileID
        }
    }

    fun getConnection(i: Int): Long? {
        return when (i) {
            0 -> top
            1 -> right
            2 -> bottom
            3 -> left
            else -> error("")
        }
    }

    fun print() {
        grid.forEach { it.map { b -> if (b) '#' else '.' }.joinToString("").also { s -> println(s) } }
    }
}