package utils.common.automata.caching

import utils.common.automata.CellularAutomataRule
import utils.common.coordinates.ICoordinates

class BaseCellularAutomataCache<Dim : ICoordinates, T> : ICellularAutomataCache<Dim, T> {

    private var rules: Map<T, List<CellularAutomataRule<T, Dim>>> = mapOf()

    override fun initialize(map: Map<Dim, T>, rules: List<CellularAutomataRule<T, Dim>>) {
        this.rules = rules.groupBy { it.source }
    }

    override fun resolve(value: T, coordinate: Dim, neighbours: Map<Dim, T>): T {
        var new: T = value
        rules[value]?.forEach { if (it.condition(coordinate, neighbours)) new = it.target }
        return new
    }

}