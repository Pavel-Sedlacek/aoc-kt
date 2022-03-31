package utils.common.automata

import utils.common.coordinates.ICoordinates

data class CellularAutomataRule<T, Dim : ICoordinates>(
    val source: T,
    val target: T,
    val condition: (current: ICoordinates, neighbors: Map<Dim, T>) -> Boolean
)