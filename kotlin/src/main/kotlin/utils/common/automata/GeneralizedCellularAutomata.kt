package utils.common.automata

import utils.collections.copy
import utils.common.automata.caching.ICellularAutomataCache
import utils.common.automata.fetching.INeighbourFetchStrategy
import utils.common.coordinates.ICoordinates

class GeneralizedCellularAutomata<T, Dim : ICoordinates>(
    private val keepHistory: Boolean = false,
    private val countIterations: Boolean = false,
    private val wrap: Boolean = false,
    private val smartCaching: ICellularAutomataCache<Dim, T>,
    private val neighbourFetchStrategy: INeighbourFetchStrategy<Dim>,
    rules: List<CellularAutomataRule<T, Dim>>,
    private var map: Map<Dim, T>
) {

    init {
        smartCaching.initialize(map, rules)
    }

    private val history = mutableListOf<Map<Dim, T>>()
    private var iterations = 0

    fun next() {
        val new = mutableMapOf<Dim, T>()
        for ((coordinate, value) in map) {
            new[coordinate] =
                smartCaching.resolve(value, coordinate, neighbourFetchStrategy.fetch(coordinate, map, wrap))
        }
        map = new
        if (keepHistory) history.add(map.copy())
        if (countIterations) iterations += 1
    }
}