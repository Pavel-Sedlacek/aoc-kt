package y2020

import java.io.File

fun main() {
    val x = Day24()
    println("Day TWENTY-FOUR : Hex-tiling floor")
    println("Part One ${x.partOneTwentyFour()}")
    println("Part Two ${x.partTwoTwentyFour()}")
}

class Day24 {

    fun run() {
        println("Day TWENTY-FOUR : Hex-tiling floor")
        println("Part One ${partOneTwentyFour()}")
        println("Part Two ${partTwoTwentyFour()}")
    }

    val input = File("src/main/resources/Day24.txt").readLines()

    fun partOneTwentyFour(): Int =
        decorateFloor().size

    fun partTwoTwentyFour(): Int =
        generateSequence(decorateFloor()) { it.nextFloor() }
            .drop(100)
            .first()
            .size

    private fun Set<Point3D>.nextFloor(): Set<Point3D> {
        val pointsToEvaluate = this + (this.flatMap { point -> point.hexNeighbors })
        return pointsToEvaluate.filter { tile ->
            val adjacentBlackTiles = tile.hexNeighbors.count { it in this }
            val black = tile in this
            when {
                black && (adjacentBlackTiles == 0 || adjacentBlackTiles > 2) -> false
                !black && adjacentBlackTiles == 2 -> true
                else -> black
            }
        }.toSet()
    }

    private fun decorateFloor(): Set<Point3D> =
        input
            .map { it.walkPath() }
            .groupBy { it }
            .filter { it.value.size % 2 == 1 }
            .keys

    private fun String.walkPath(): Point3D =
        "([ns]?[ew])".toRegex()
            .findAll(this)
            .map { it.value }
            .fold(Point3D.ORIGIN) { last, dir ->
                last.hexNeighbor(dir)
            }
}


data class Point3D(val x: Int, val y: Int, val z: Int) {
    val hexNeighbors: List<Point3D> by lazy {
        HEX_OFFSETS.map { this + it.value }
    }

    operator fun plus(other: Point3D): Point3D =
        Point3D(x + other.x, y + other.y, z + other.z)

    fun hexNeighbor(dir: String): Point3D =
        if (dir in HEX_OFFSETS) HEX_OFFSETS.getValue(dir) + this
        else error("nope")

    companion object {
        val ORIGIN = Point3D(0, 0, 0)
        val HEX_OFFSETS = mapOf(
            "e" to Point3D(1, -1, 0),
            "w" to Point3D(-1, 1, 0),
            "ne" to Point3D(1, 0, -1),
            "nw" to Point3D(0, 1, -1),
            "se" to Point3D(0, -1, 1),
            "sw" to Point3D(-1, 0, 1),
        )
    }
}