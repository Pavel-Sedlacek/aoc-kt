package utils.collections

import utils.common.cellularAutomata.AutomataDirection
import utils.common.wrap


fun <T, U> Map<T, U>.copy(mut: Boolean = false) = if (mut) this.toMutableMap() else this.toMap()


fun <V> Map<Coordinates, V>.surroundingCoordinates(
    coordinate: Coordinates,
    default: V,
    wrap: Boolean = false,
    width: Int = 0,
    height: Int = 0
): Map<AutomataDirection, Pair<Coordinates, V>> {
    val r = mutableMapOf<AutomataDirection, Pair<Coordinates, V>>()
    for (x in -1..1) {
        for (y in -1..1) {
            val coord = Coordinates(
                (coordinate.x + x).let { if (wrap) it.wrap(0, width) else it },
                (coordinate.y + y).let { if (wrap) it.wrap(0, height) else it }
            )
            r[AutomataDirection.from(x, y)] = coord to this.getOrDefault(coord, default)
        }
    }
    return r
}

fun <K> subMatch(c: Map<Coordinates, K>, other: Map<Coordinates, K>?): Boolean {
    if (other == null) return false
    for ((key, value) in c) {
        if (other[key] != value) return false
    }
    return true
}