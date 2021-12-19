package utils.helpers.y2021


data class TargetArea(val x1: Int, val x2: Int, val y1: Int, val y2: Int) {
    operator fun contains(point: Pair<Int, Int>) = point.first in x1..x2 && point.second in y1..y2
}

data class Velocity(val x: Int, val y: Int) {
    fun willBeWithin(target: TargetArea) =
        (seqX() zip seqY()).takeWhile { (x, y) -> x <= target.x2 && y >= target.y1 }.any { it in target }

    private fun seqX() = generateSequence(0 to x) { (x, velX) -> x + velX to maxOf(0, velX - 1) }.map { it.first }

    private fun seqY() = generateSequence(0 to y) { (y, velY) -> y + velY to velY - 1 }.map { it.first }
}

fun Int.sequenceSum(): Int {
    return (1 + this) * this / 2
}