package y2020

import utils.Day
import utils.Helpers
import utils.Helpers.Tile
import utils.Helpers.calcWaterRoughness
import utils.common.opposite
import utils.readers.asLinesSplitBy
import java.util.*
import kotlin.math.roundToInt
import kotlin.math.sqrt

class Day20 : Day<Long> {

    private val input = file.asLinesSplitBy("\n\n").map { it.lines() }.map {
        Tile(
            it.first().split(" ").last().dropLast(1).toLong(),
            it.drop(1).map { s -> s.map { c -> c == '#' } })
    }

    override fun runAll() = super.run({ partOne(arrangeTiles(input)) }, {partTwo(arrangeTiles(input))})

    private fun partOne(input: List<List<Tile>>): Long {
        return input.first().first().id * input.first().last().id * input.last().first().id * input.last().last().id
    }

    private fun partTwo(input: List<List<Tile>>): Long {
        val x = Tile(0, input.map { row ->
            row.map { tile ->
                tile.removeBorder()
                tile.grid
            }.flatMap { it.mapIndexed { i, list -> IndexedValue(i, list) } }
                .groupBy({ (i, _) -> i }, { (_, v) -> v })
                .map { (_, v) -> v.reduce { acc, list -> acc + list } }
        }.reduce { acc, list -> acc + list })

        return calcWaterRoughness(x).toLong()
    }

    private fun arrangeTiles(tiles: List<Tile>): List<List<Tile>> {
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

        val image = List(sqrt(tiles.size.toDouble()).roundToInt()) {
            MutableList(sqrt(tiles.size.toDouble()).roundToInt()) {
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
}