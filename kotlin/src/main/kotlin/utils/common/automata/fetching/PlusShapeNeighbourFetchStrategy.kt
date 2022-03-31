package utils.common.automata.fetching

import utils.common.coordinates.Coordinates2D

open class PlusShapeNeighbourFetchStrategy private constructor() : INeighbourFetchStrategy<Coordinates2D> {

    companion object : PlusShapeNeighbourFetchStrategy()

    override fun <T> fetch(location: Coordinates2D, map: Map<Coordinates2D, T>, wrap: Boolean): Map<Coordinates2D, T> {
        val x: MutableMap<Coordinates2D, T> = mutableMapOf()
        val neighbors = location.directNeighbors()
        neighbors.forEach { i ->
            map[i].also {
                if (it != null) x[i] = it
            }
        }
        return x
    }

}