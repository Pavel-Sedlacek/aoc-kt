package utils.common.automata.caching

import utils.common.automata.CellularAutomataRule
import utils.common.coordinates.ICoordinates

interface ICellularAutomataCache<Dim : ICoordinates, T> {
    fun initialize(map: Map<Dim, T>, rules: List<CellularAutomataRule<T, Dim>>)
    fun resolve(value: T, coordinate: Dim, neighbours: Map<Dim, T>): T

}